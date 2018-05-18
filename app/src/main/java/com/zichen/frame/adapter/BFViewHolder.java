package com.zichen.frame.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zichen.frame.http.HttpMethods;
import com.zichen.frame.util.Utils;

import java.util.List;

public class BFViewHolder extends BaseViewHolder {


    public BFViewHolder(View view) {
        super(view);
    }


    /**
     * 通过Glide设置网络图片
     *
     * @param viewId
     * @param imageUrl
     * @return
     */
    public BaseViewHolder setImageResource(int viewId, String imageUrl) {
        ImageView view = getView(viewId);
        Glide.with(Utils.getContext()).load(HttpMethods.getPicturePath(imageUrl)).into(view);
        return this;
    }
}
