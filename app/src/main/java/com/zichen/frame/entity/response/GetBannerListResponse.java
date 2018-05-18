package com.zichen.frame.entity.response;

import java.io.Serializable;
import java.util.List;


/**
 * 获取banner列表.服务端响应
 */
public class GetBannerListResponse extends MobileResponse {

    private List<ElementBannerList> bannerList;

    public static class ElementBannerList implements Serializable {
        private String titile;
        private String link;
        private String picture;
        private String id;

        /**
         * @return 标题
         */
        public String getTitile() {
            return titile;
        }

        public void setTitile(String titile) {
            this.titile = titile;
        }

        /**
         * @return 链接
         */
        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        /**
         * @return 图片名称
         */
        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        /**
         * @return bannerId
         */
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "ElementBannerList{" +
                    "titile='" + titile + '\'' +
                    ", link='" + link + '\'' +
                    ", picture='" + picture + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }


    /**
     * @return banner集合
     */
    public List<ElementBannerList> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<ElementBannerList> bannerList) {
        this.bannerList = bannerList;
    }

    @Override
    public String toString() {
        return "GetBannerListResponse{" +
                "bannerList=" + bannerList +
                '}';
    }
}