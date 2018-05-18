package com.zichen.frame.entity.request;

import java.io.Serializable;

/**
 * 首页获取新闻资讯和民生公告.客户端请求
 *
 * @author 代码生成器v1.0
 */
public class GetNewsListRequest extends MobileRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private String pageNo;

    /**
     * 类型（1：首页 2：新闻资讯列表 3：民生观点列表）
     */
    private String type;

    public GetNewsListRequest() {
        super();
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}