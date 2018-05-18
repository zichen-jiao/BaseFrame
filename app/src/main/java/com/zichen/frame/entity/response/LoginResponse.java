package com.zichen.frame.entity.response;

import java.io.Serializable;

/**
 * 用户登录.服务端响应
 *
 * @author 代码生成器v1.0
 */
public class LoginResponse extends MobileResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String refreshToken;

    /**
     * @return 刷新令牌
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "refreshToken='" + refreshToken + '\'' +
                "} " + super.toString();
    }
}