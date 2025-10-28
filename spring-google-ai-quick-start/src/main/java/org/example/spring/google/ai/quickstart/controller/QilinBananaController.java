package org.example.spring.google.ai.quickstart.controller;

import lombok.RequiredArgsConstructor;
import org.example.spring.google.ai.quickstart.dto.CommonResult;
import org.example.spring.google.ai.quickstart.dto.TryOnReq;
import org.example.spring.google.ai.quickstart.service.IFittingRoomService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Description: 试衣服务接口
 *
 * @author Song.Z
 * @since 2025/10/22
 */
@RestController
@RequestMapping("/v1/aiagent/fitingroom")
@RequiredArgsConstructor
public class QilinBananaController {

    private final IFittingRoomService fittingRoomService;
    @PostMapping("/try-on")
    public CommonResult<String> tryOn(@Validated @RequestBody TryOnReq req) {
        return fittingRoomService.tryOn(req.getModelImageBase64(), req.getClothImageBase64());
    }
}
