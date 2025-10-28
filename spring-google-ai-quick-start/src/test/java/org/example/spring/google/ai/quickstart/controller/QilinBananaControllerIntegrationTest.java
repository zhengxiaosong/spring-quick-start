package org.example.spring.google.ai.quickstart.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.spring.google.ai.quickstart.dto.TryOnReq;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value; // 新增
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern; // 新增

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 集成测试：真实调用 Google Gemini API（使用配置文件 application-local.yml 中的 google.gemini.api-key）。
 * 运行：mvn -f spring-google-ai-quick-start/pom.xml test -Dtest=QilinBananaControllerIntegrationTest
 */
@SpringBootTest
@AutoConfigureMockMvc
public class QilinBananaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${google.gemini.api-key:}")
    private String apiKey; // 从配置读取，若为空则跳过测试

    private static final Pattern DATA_URL_PREFIX = Pattern.compile("^data:image/[^;]+;base64,"); // 新增：检测前缀

    private String getImageBase64(String relPath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(relPath));
        return Base64Utils.encodeToString(bytes);
    }

    @Test
    void testRealGeminiTryOnAndSaveImage() throws Exception {
        Assumptions.assumeTrue(StringUtils.hasText(apiKey), "配置中 google.gemini.api-key 为空，跳过真实调用测试");

        String modelBase64 = getImageBase64("src/test/resources/asserts/model.png");
        String clothBase64 = getImageBase64("src/test/resources/asserts/dress9.png");
        TryOnReq req = new TryOnReq();
        req.setModelImageBase64(modelBase64);
        req.setClothImageBase64(clothBase64);

        String respJson = mockMvc.perform(post("/v1/aiagent/fitingroom/try-on")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JsonNode root = objectMapper.readTree(respJson);
        String code = root.path("code").asText();
        String dataBase64 = root.path("data").asText();
        String msg = root.path("msg").asText();

        if (!"200".equals(code)) {
            throw new AssertionError("调用失败 code=" + code + " msg=" + msg);
        }
        if (!StringUtils.hasText(dataBase64)) {
            throw new AssertionError("返回的合成图片Base64为空");
        }

        byte[] imageBytes = decodeImageBase64(dataBase64); // 使用安全解码
        Path outDir = Paths.get("src/test/resources/asserts/generated");
        Files.createDirectories(outDir);
        String fileName = "tryon_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".png";
        Path outFile = outDir.resolve(fileName);
        Files.write(outFile, imageBytes);

        if (!Files.exists(outFile) || Files.size(outFile) == 0) {
            throw new AssertionError("图片保存失败: " + outFile);
        }
        System.out.println("生成图片已保存: " + outFile.toAbsolutePath());
    }

    /**
     * 安全解码：支持 data:image/...;base64, 前缀；去除所有空白字符。
     */
    private byte[] decodeImageBase64(String raw) {
        if (!StringUtils.hasText(raw)) {
            return new byte[0];
        }
        String work = raw.trim();
        if (work.startsWith("data:")) {
            int comma = work.indexOf(',');
            if (comma > 0) {
                work = work.substring(comma + 1);
            }
        } else {
            // 兼容可能出现的前缀大小写差异 data:Image/png... 等情况
            if (DATA_URL_PREFIX.matcher(work.toLowerCase(Locale.ROOT)).find()) {
                int comma = work.indexOf(',');
                if (comma > 0) {
                    work = work.substring(comma + 1);
                }
            }
        }
        // 去除空白与换行
        work = work.replaceAll("\\s", "");
        return Base64Utils.decodeFromString(work);
    }
}
