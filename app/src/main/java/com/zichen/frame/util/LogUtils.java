package com.zichen.frame.util;

import com.google.gson.Gson;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.qmuiteam.qmui.QMUILog;
import com.zichen.frame.BuildConfig;
import com.zichen.frame.entity.request.MobileRequest;
import com.zichen.frame.entity.response.MobileResponse;

import java.util.List;
import java.util.Map;

/**
 * author : lyw
 * time : 2017/7/28
 * desc : Log工具类
 * version: 1.0
 */
public class LogUtils {
    private static final String TAG = "LOG";

    public static void init() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
//                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//                .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
//                .tag("My custom tag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new DiskLogAdapter(formatStrategy));
//        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static void v(Object obj) {
        if (BuildConfig.PROXY)
            Logger.v(obj.toString());
    }

    public static void d(Object obj) {
        if (BuildConfig.PROXY)
            Logger.d(obj.toString());
//        QMUILog.d(TAG, obj.toString());
//            Log.d(TAG, obj.toString());
    }

    public static void i(Object obj) {
        if (BuildConfig.PROXY)
            Logger.i(obj.toString());
//            QMUILog.i(TAG, obj.toString());
//            Log.i(TAG, obj.toString());
    }

    public static void e(Object obj) {
        if (BuildConfig.PROXY)
//            QMUILog.e(TAG, obj.toString());
            Logger.e(obj.toString());
//            Log.e(TAG, obj.toString());
    }

    public static void json(String json) {
        if (BuildConfig.PROXY) {
//            QMUILog.d(TAG, json);
            Logger.json(json);
        }
    }

    public static void d(List list) {
        if (BuildConfig.PROXY) {
            Logger.d(list);
        }
    }

    public static void d(Map map) {
        if (BuildConfig.PROXY) {
            Logger.d(map);
        }
    }

    public static void json(MobileRequest request) {
        json(new Gson().toJson(request));
    }

    public static void json(MobileResponse response) {
        json(new Gson().toJson(response));
    }
}
