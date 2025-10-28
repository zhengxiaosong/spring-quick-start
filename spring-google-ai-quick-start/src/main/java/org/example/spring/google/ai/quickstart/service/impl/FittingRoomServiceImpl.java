package org.example.spring.google.ai.quickstart.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.spring.google.ai.quickstart.dto.CommonResult;
import org.example.spring.google.ai.quickstart.enums.GlobalErrorCodeConstants;
import org.example.spring.google.ai.quickstart.service.IFittingRoomService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 调用 Google Gemini 图片生成试衣接口实现
 * 支持：入参可带/不带 data:image/...;base64, 前缀；返回自动补 data:image/png;base64, 前缀。
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FittingRoomServiceImpl implements IFittingRoomService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String DEFAULT_PROMPT = "Create a professional e-commerce photo. Have the person from the second image wear the item from the first image. Generate a realistic shot with proper lighting and shadows matching the environment.";
    private static final String GEMINI_ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-image:generateContent";

    @Value("${google.gemini.api-key:}")
    private String apiKey;

    private static final Pattern DATA_URL_PREFIX = Pattern.compile("^data:image/([a-zA-Z0-9+.-]+);base64,");

    @Override
    public CommonResult<String> tryOn(String modelImageBase64, String clothImageBase64) {
        if (!StringUtils.hasText(apiKey)) {
            return CommonResult.error(GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR.getCode(), "Google Gemini API Key缺失");
        }
        if (!StringUtils.hasText(modelImageBase64) || !StringUtils.hasText(clothImageBase64)) {
            return CommonResult.error(GlobalErrorCodeConstants.BAD_REQUEST.getCode(), "图片Base64不能为空");
        }
        // 清洗 & 检测 MIME 类型 (默认 PNG)
        String modelMime = detectMime(modelImageBase64);
        String clothMime = detectMime(clothImageBase64);
        String modelSanitized = sanitizeBase64(modelImageBase64);
        String clothSanitized = sanitizeBase64(clothImageBase64);
        try {
            // 顺序：第一张衣服，第二张模特
            Map<String, Object> requestBody = buildRequest(clothSanitized, clothMime, modelSanitized, modelMime, DEFAULT_PROMPT);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("x-goog-api-key", apiKey);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.exchange(GEMINI_ENDPOINT, HttpMethod.POST, entity, String.class);
            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                log.error("Gemini试衣接口调用失败 status={} body={} ", response.getStatusCode(), response.getBody());
                return CommonResult.error(GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR.getCode(), "试衣生成失败");
            }
            String mergedImageBase64 = parseBase64(response.getBody());
            if (!StringUtils.hasText(mergedImageBase64)) {
                return CommonResult.error(GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR.getCode(), "未获取到生成图片数据");
            }
            // 补前缀，若已包含则直接返回
            if (!mergedImageBase64.startsWith("data:image")) {
                mergedImageBase64 = "data:image/png;base64," + mergedImageBase64;
            }
            return CommonResult.success(mergedImageBase64);
        } catch (Exception e) {
            log.error("调用Gemini试衣接口异常: {}", e.getMessage(), e);
            return CommonResult.error(GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR.getCode(), "试衣生成异常:" + e.getMessage());
        }
    }

    private Map<String, Object> buildRequest(String clothBase64, String clothMime, String modelBase64, String modelMime, String prompt) {
        Map<String, Object> inlineCloth = new HashMap<>();
        inlineCloth.put("mime_type", clothMime);
        inlineCloth.put("data", clothBase64);
        Map<String, Object> partCloth = new HashMap<>();
        partCloth.put("inline_data", inlineCloth);

        Map<String, Object> inlineModel = new HashMap<>();
        inlineModel.put("mime_type", modelMime);
        inlineModel.put("data", modelBase64);
        Map<String, Object> partModel = new HashMap<>();
        partModel.put("inline_data", inlineModel);

        Map<String, Object> partPrompt = new HashMap<>();
        partPrompt.put("text", prompt);

        List<Map<String, Object>> parts = Arrays.asList(partCloth, partModel, partPrompt);
        Map<String, Object> content = new HashMap<>();
        content.put("parts", parts);
        Map<String, Object> root = new HashMap<>();
        root.put("contents", Collections.singletonList(content));
        return root;
    }

    private String parseBase64(String json) {
        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode candidates = root.get("candidates");
            if (candidates == null || !candidates.isArray() || candidates.size() == 0) {
                return null;
            }
            for (JsonNode candidate : candidates) {
                JsonNode content = candidate.get("content");
                if (content == null) continue;
                JsonNode parts = content.get("parts");
                if (parts == null || !parts.isArray()) continue;
                for (JsonNode part : parts) {
                    JsonNode inlineData = part.get("inlineData");
                    if (inlineData == null) {
                        inlineData = part.get("inline_data"); // 兼容另一种命名
                    }
                    if (inlineData != null) {
                        JsonNode dataNode = inlineData.get("data");
                        if (dataNode != null && dataNode.isTextual()) {
                            return dataNode.asText();
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("解析Gemini返回值异常: {}", e.getMessage(), e);
        }
        return null;
    }

    private String sanitizeBase64(String base64) {
        if (!StringUtils.hasText(base64)) {
            return base64;
        }
        Matcher m = DATA_URL_PREFIX.matcher(base64);
        String noPrefix = m.find() ? base64.substring(m.end()) : base64;
        return noPrefix.replaceAll("\\s", "");
    }

    private String detectMime(String base64) {
        if (!StringUtils.hasText(base64)) {
            return "image/png";
        }
        Matcher m = DATA_URL_PREFIX.matcher(base64);
        if (m.find()) {
            return "image/" + m.group(1).toLowerCase(Locale.ROOT);
        }
        return "image/png"; // 默认当成 png
    }
}
