package org.example.spring.google.ai.quickstart.dto;

import lombok.Data;

/**
 * TryOnReq
 * 入参：模特图、衣服图（Base64）
 */
@Data
public class TryOnReq {

    private String modelImageBase64;

    private String clothImageBase64;

    private String prompt;
}
