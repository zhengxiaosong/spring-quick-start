package org.example.spring.google.ai.quickstart.enums;

import lombok.Data;

/**
 * Description:
 *
 * @author Song.Z
 * @since 2025/10/22
 */
@Data
public class ErrorCode implements IErrorCode {

    /**
     * 错误码
     */
    private final String code;
    /**
     * 错误提示
     */
    private final String msg;

    public ErrorCode(Integer code, String message) {
        this.code = Integer.toString(code);
        this.msg = message;
    }

}