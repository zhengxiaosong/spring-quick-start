package org.example.spring.google.ai.quickstart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.spring.google.ai.quickstart.dto.CommonResult;
import org.example.spring.google.ai.quickstart.dto.TryOnReq;
import org.example.spring.google.ai.quickstart.service.IFittingRoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 使用 MockBean 隔离 Service 层，避免真实外部调用。
 */
@WebMvcTest(QilinBananaController.class)
public class QilinBananaControllerMockTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private IFittingRoomService fittingRoomService;

    private String getImageBase64(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        return Base64Utils.encodeToString(bytes);
    }

    @Test
    void testTryOnSuccess() throws Exception {
        // 读取测试资源图片
        String modelBase64 = getImageBase64("src/test/resources/asserts/model.png");
        String clothBase64 = getImageBase64("src/test/resources/asserts/dress9.png");
        // Mock Service 返回成功结果
        when(fittingRoomService.tryOn(anyString(), anyString()))
                .thenReturn(CommonResult.success("fakeResultBase64"));
        // 构造请求
        TryOnReq req = new TryOnReq();
        req.setModelImageBase64(modelBase64);
        req.setClothImageBase64(clothBase64);
        // 调用接口并断言
        mockMvc.perform(post("/v1/aiagent/fitingroom/try-on")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.data").value("fakeResultBase64"))
                .andExpect(jsonPath("$.msg").value("成功"));
    }
}

