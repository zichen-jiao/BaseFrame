package com.zichen.frame;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.style.DynamicDrawableSpan;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.zichen.frame.manager.QDUpgradeManager;
import com.zichen.frame.util.CacheUtil;
import com.zichen.frame.util.LogUtils;
import com.zichen.frame.util.Utils;
import com.zichen.frame.util.user.UserInfoKeeper;

import java.text.SimpleDateFormat;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

/**
 * @作者 : zichen
 * @日期 : 2018/5/17
 * @注释 :
 * @version: 1.0
 */
public class App extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.qmui_config_color_50_white, R.color.qmui_config_color_blue);//全局设置主题颜色
            return new ClassicsHeader(context).setTimeFormat(new SimpleDateFormat("更新于 MM-dd HH:mm", Locale.CHINA));//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    public static Context getContext() {
        return context;
    }

    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        // 初始化logger
        LogUtils.init();
        LeakCanary.install(this);
        Utils.init(getApplicationContext());
        QDUpgradeManager.getInstance(this).check();
        UserInfoKeeper.getInstance().init(null);//从缓存文件中取出用户数据
        Toasty.Config.getInstance().setErrorColor(Color.RED).setSuccessColor(Color.GREEN).setTextColor(Color.WHITE).apply();

    }
}
