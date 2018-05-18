package com.zichen.frame.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * 首页获取新闻资讯和民生公告.服务端响应
 *
 * @author 代码生成器v1.0
 */
public class GetNewsListResponse extends MobileResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<News> newsList;

    public static class News implements Parcelable {
        private String title;
        private String logo;
        private String contant;
        private String time;
        private String link;
        /**
         * 新闻类型  1：公司新闻  2：民生视点
         */
        private String type;
        private String id;

        public boolean isCompanyNews() {
            return "1".equals(type);
        }

        /**
         * @return 标题
         */
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        /**
         * @return logo
         */
        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        /**
         * @return 内容
         */
        public String getContant() {
            return contant;
        }

        public void setContant(String contant) {
            this.contant = contant;
        }

        /**
         * @return 时间
         */
        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
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
         * @return 类型
         */
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        /**
         * @return 新闻资讯/民生观点详情id
         */
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "News{" +
                    "title='" + title + '\'' +
                    ", logo='" + logo + '\'' +
                    ", contant='" + contant + '\'' +
                    ", time='" + time + '\'' +
                    ", link='" + link + '\'' +
                    ", type='" + type + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.logo);
            dest.writeString(this.contant);
            dest.writeString(this.time);
            dest.writeString(this.link);
            dest.writeString(this.type);
            dest.writeString(this.id);
        }

        public News() {
        }

        protected News(Parcel in) {
            this.title = in.readString();
            this.logo = in.readString();
            this.contant = in.readString();
            this.time = in.readString();
            this.link = in.readString();
            this.type = in.readString();
            this.id = in.readString();
        }

        public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
            @Override
            public News createFromParcel(Parcel source) {
                return new News(source);
            }

            @Override
            public News[] newArray(int size) {
                return new News[size];
            }
        };
    }


    /**
     * @return 新闻资讯、民生观点集合
     */
    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public String toString() {
        return "GetNewsListResponse{" +
                "newsList=" + newsList +
                "} " + super.toString();
    }
}