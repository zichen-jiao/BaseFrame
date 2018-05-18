package com.zichen.frame.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zichen.frame.R;
import com.zichen.frame.constants.Config;
import com.zichen.frame.constants.ConstantsKey;
import com.zichen.frame.entity.request.GetBannerListRequest;
import com.zichen.frame.entity.request.MobileRequest;
import com.zichen.frame.entity.response.GetBannerListResponse;
import com.zichen.frame.entity.response.GetProductsForIndexResponse;
import com.zichen.frame.fragment.DetailFragment;
import com.zichen.frame.http.HttpMethods;
import com.zichen.frame.http.ObserverOnNextListener;
import com.zichen.frame.http.ProgressObserver;
import com.zichen.frame.util.CacheUtil;
import com.zichen.frame.util.DeviceUtils;
import com.zichen.frame.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者 : zichen
 * @日期 : 2018/5/18
 * @注释 : 主界面中首页的Fragment实现
 * @version: 1.0
 */
public class HomeFragmentController extends HomeController {
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.banner)
    Banner banner;

    @Override
    protected View initViews() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home_tab_layout, null);
        ButterKnife.bind(this, root);
        initTopBar();
        initView();
        return root;
    }

    @Override
    protected void initData() {
        LogUtils.d("首页：initData");
        getBanner();
    }


    private void initTopBar() {
        topbar.setTitle("首页");
        topbar.addRightTextButton("详情", R.id.topbar_right_message_button).setOnClickListener(v -> {
            showToastyNormal("详情");
            startFragment(DetailFragment.newInstance("首页详情"));
        });
    }

    private void initView() {


    }

    /**
     * 获取banner数据
     */
    private void getBanner() {
        ObserverOnNextListener<GetBannerListResponse> getBannerListener = new ObserverOnNextListener<GetBannerListResponse>() {
            @Override
            public void onNext(GetBannerListResponse getBannerListResponse) {
                LogUtils.json(getBannerListResponse);
//            String json = toJson(getBannerListResponse);
                CacheUtil.getInstance().put(ConstantsKey.CachKey.BANNER_FROMCACH, getBannerListResponse);
                initBanner(getBannerListResponse);
            }

            @Override
            public void onComplete() {

            }
        };

        GetBannerListRequest request = new GetBannerListRequest();
        request.setEquipmentId(DeviceUtils.getEquipmentId());
        request.setEquipmentType(DeviceUtils.getEquipmentType());
        HttpMethods.getInstance().getBanner(new ProgressObserver<>(getActivity(), true, getBannerListener), request);
    }

    /**
     * 初始化banner
     */
    private void initBanner(GetBannerListResponse response) {
        List<GetBannerListResponse.ElementBannerList> bannerList = response.getBannerList();
        if (bannerList == null || bannerList.size() == 0) {
            return;
        }
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.updateBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setDelayTime(Config.BANNER_DELAYTIME);
        List<String> image = new ArrayList<>();
        for (int i = 0; i < bannerList.size(); i++) {
            String link = HttpMethods.getPicturePath(bannerList.get(i).getPicture());
            image.add(link);
        }
        banner.setImages(image);
        banner.isAutoPlay(true);
        banner.start();
    }

    public static HomeFragmentController newInstance() {

        Bundle args = new Bundle();
        HomeFragmentController fragment = new HomeFragmentController();
        fragment.setArguments(args);
        return fragment;
    }
}
