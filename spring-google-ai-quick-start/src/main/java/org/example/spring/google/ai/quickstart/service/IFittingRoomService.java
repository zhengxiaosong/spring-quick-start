package org.example.spring.google.ai.quickstart.service;

import org.example.spring.google.ai.quickstart.dto.CommonResult;

/**
 * Fitting room service: 调用 Google Gemini 图片试衣接口
 */
public interface IFittingRoomService {

    /**
     * 模特图片+衣服图片试穿合成
     * @param modelImageBase64 模特图片Base64
     * @param clothImageBase64 衣服图片Base64
     * @param prompt 试衣提示词
     * @return 合成后图片Base64
     */
    CommonResult<String> tryOn(String modelImageBase64, String clothImageBase64);
}
