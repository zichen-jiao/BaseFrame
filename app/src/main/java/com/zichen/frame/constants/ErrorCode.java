package com.zichen.frame.constants;

/**
 * author : lyw
 * time : 2017/8/28
 * desc : 服务端响应返回的错误码
 * version: 1.0
 */
public class ErrorCode {

    /**
     * 内部错误
     */
    public static String ERROR_SYSTEM = "ERROR_SYSTEM";

    /**
     * 未登录
     */
    public static String ERROR_NOAUTH = "ERROR_NOAUTH";

    /**
     * 您已在别处登录此处已下线
     */
    public static String ERROR_CONCURRENT = "ERROR_CONCURRENT";
}
