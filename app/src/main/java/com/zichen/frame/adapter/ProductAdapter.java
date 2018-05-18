package com.zichen.frame.adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zichen.frame.R;
import com.zichen.frame.entity.response.GetNewsListResponse;
import com.zichen.frame.http.HttpMethods;

import java.util.List;

public class ProductAdapter extends BaseQuickAdapter<GetNewsListResponse.News, BFViewHolder> {
    public ProductAdapter(int layoutResId, @Nullable List<GetNewsListResponse.News> data) {
        super(layoutResId, data);
    }

    public ProductAdapter(@Nullable List<GetNewsListResponse.News> data) {
        super(data);
    }

    public ProductAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BFViewHolder helper, GetNewsListResponse.News item) {
        if (null != item) {
            helper.setText(R.id.tv_news_title, item.getTitle());
            helper.setText(R.id.tv_news_content, item.getContant());
            helper.setText(R.id.tv_news_date, item.getTime());
            helper.setImageResource(R.id.img_news, item.getLogo());
        }
    }
}
