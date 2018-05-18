package com.zichen.frame.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import com.qmuiteam.qmui.util.QMUIPackageHelper;

/**
 * author : lyw
 * time : 2017/8/25
 * desc : 获取设备相关信息的工具类
 * version: 1.0
 */
public class DeviceUtils {

    /**
     * 客户端类型  1.android  2.ios
     */
    public static String getEquipmentType() {
        return "1";
    }

    /**
     * 本机品牌
     */
    public static String getEquipmentBrand() {
        return Build.BRAND;
    }

    /**
     * 设备唯一id
     * <p>
     * 获取唯一id的几种方法，各有优缺点
     * <p>
     * 1. 使用TelephonyManage认得到IMEI，需要额外权限READ_PHOTO_STATE
     * <p>
     * 2.MAC address 需要wifi或蓝牙 ，需要额外权限
     * <p>
     * 3.SerialNumber  Build.SERIAL， 不是所有的设备都有这个值
     * <p>
     * 4.Secure Android ID
     * <p>
     * 5.Random UUID 标志应用安装
     */
    @SuppressLint("HardwareIds")
    public static String getEquipmentId() {
        // TODO: 下面的这种方法有可能由于手机厂商或者root等原因导致获取不到正确的值。
        return Settings.Secure.getString(Utils.getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    /**
     * 设备型号
     */
    public static String getEquipmentVersion() {
        return Build.MODEL;
    }

    /**
     * 客户端版本号
     */
    public static String getClientVersion() {
        return QMUIPackageHelper.getAppVersion(Utils.getContext());
    }
}
