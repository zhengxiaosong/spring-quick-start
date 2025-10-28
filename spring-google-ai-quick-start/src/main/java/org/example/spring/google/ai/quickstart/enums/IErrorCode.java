package org.example.spring.google.ai.quickstart.enums;

/**
 * Description: 异常码接口
 * <br/>
 * IErrorCode
 *
 * @author laiql
 * @date 2021/12/22 4:17 下午
 */
public interface IErrorCode {
    /**
     * 状态码
     *
     * @return integer
     */
    String getCode();

    /**
     * 响应描述信息
     *
     * @return string
     */
    String getMsg();
}
