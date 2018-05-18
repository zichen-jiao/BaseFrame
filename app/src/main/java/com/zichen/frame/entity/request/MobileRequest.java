package com.zichen.frame.entity.request;

import com.zichen.frame.entity.response.MobileResponse;

import java.io.Serializable;

/**
 * 报文请求基类
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(Include.NON_NULL)
public class MobileRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Class<? extends MobileResponse> getResponseClass() {
        return null;
    }

    @Override
    public String toString() {
        return "MobileRequest{" +
                "sessionId='" + sessionId + '\'' +
                '}';
    }
}
