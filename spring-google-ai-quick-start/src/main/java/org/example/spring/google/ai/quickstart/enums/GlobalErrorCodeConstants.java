package org.example.spring.google.ai.quickstart.enums;

/**
 * Description:
 *
 * @author Song.Z
 * @since 2025/10/22
 */
public interface GlobalErrorCodeConstants {

    ErrorCode SUCCESS = new ErrorCode(200, "成功");

    // ========== 客户端错误段 ==========

    ErrorCode BAD_REQUEST = new ErrorCode(400, "请求参数不正确");
    ErrorCode UNAUTHORIZED = new ErrorCode(401, "账号未登录");
    ErrorCode FORBIDDEN = new ErrorCode(403, "没有该操作权限");
    ErrorCode NOT_FOUND = new ErrorCode(404, "请求未找到");
    ErrorCode METHOD_NOT_ALLOWED = new ErrorCode(405, "请求方法不正确");
    /**
     * 并发请求，不允许
     */
    ErrorCode LOCKED = new ErrorCode(423, "请求失败，请稍后重试");
    ErrorCode TOO_MANY_REQUESTS = new ErrorCode(429, "请求过于频繁，请稍后重试");

    // ========== 服务端错误段 ==========

    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500, "系统异常");
    ErrorCode NOT_IMPLEMENTED = new ErrorCode(501, "功能未实现/未开启");
    ErrorCode NOT_EXTEND = new ErrorCode(510, "扩展点插件调用异常");

    // ========== 自定义错误段 ==========
    /**
     * 重复请求
     */
    ErrorCode REPEATED_REQUESTS = new ErrorCode(900, "重复请求，请稍后重试");

    /**
     * 是否为服务端错误，参考 HTTP 5XX 错误码段
     *
     * @param code 错误码
     * @return 是否
     */
    static boolean isServerErrorCode(String code) {
        return code != null
                && Integer.parseInt(code) >= Integer.parseInt(INTERNAL_SERVER_ERROR.getCode()) && Integer.parseInt(code) <= Integer.valueOf(INTERNAL_SERVER_ERROR.getCode()) + 99;
    }

}
