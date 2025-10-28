package org.example.spring.google.ai.quickstart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.example.spring.google.ai.quickstart.enums.GlobalErrorCodeConstants;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * Description:
 *
 * @author Song.Z
 * @since 2025/10/22
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = -4027497937229515356L;
    /**
     * 错误码
     **/
    private String code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 错误提示，用户可阅读
     */
    private String msg;

    public static <T> CommonResult<T> error(String code, String message) {
        Assert.isTrue(!GlobalErrorCodeConstants.SUCCESS.getCode().equals(code), "code 必须是错误的！");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.msg = message;
        return result;
    }

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = GlobalErrorCodeConstants.SUCCESS.getCode();
        result.data = data;
        result.msg = GlobalErrorCodeConstants.SUCCESS.getMsg();
        return result;
    }
}
