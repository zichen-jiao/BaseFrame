package com.zichen.frame.entity.response;

import com.zichen.frame.entity.Product;

import java.util.List;

/**
 * 获取首页产品.服务端响应
 */
public class GetProductsForIndexResponse extends MobileResponse {

    private List<Product> indexProductList;

    /**
     * @return 首页产品集合
     */
    public List<Product> getIndexProductList() {
        return indexProductList;
    }

    @Override
    public String toString() {
        return "GetProductsForIndexResponse{" +
                "indexProductList=" + indexProductList +
                "} " + super.toString();
    }
}