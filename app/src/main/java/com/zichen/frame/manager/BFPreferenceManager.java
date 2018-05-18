package com.zichen.frame.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.zichen.frame.util.Utils;
import com.zichen.frame.util.user.UserInfoKeeper;

/**
 * 基础框架中SharedPreferences的管理类
 * Created by zichen on 2018/5/16.
 */

public class BFPreferenceManager {
    private static SharedPreferences sPreferences;
    private static SharedPreferences.Editor editor;
    private static BFPreferenceManager sQDPreferenceManager = null;

    private static final String APP_VERSION_CODE = "app_version_code";
    private static final String USER_PHONENUMBER_CODE = "user_phonenumber_code";
    private static final String USER_SESSION_CODE = "user_session_code";
    private static final String USER_PASSWORD_CODE = "user_password_code";

    private BFPreferenceManager(Context context) {
        sPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        editor = sPreferences.edit();
    }

    public static final BFPreferenceManager getInstance() {
        if (sQDPreferenceManager == null) {
            sQDPreferenceManager = new BFPreferenceManager(Utils.getContext());
        }
        return sQDPreferenceManager;
    }

    /**
     * 设置APP版本号
     *
     * @param code
     */
    public void setAppVersionCode(int code) {
        editor.putInt(APP_VERSION_CODE, code);
        editor.apply();
    }

    /**
     * 取得保存的版本号
     *
     * @return
     */
    public int getVersionCode() {
        return sPreferences.getInt(APP_VERSION_CODE, QDUpgradeManager.INVALIDATE_VERSION_CODE);
    }

    /**
     * 保存最后一次访问接口的时间
     */
    public void saveTheLastTimeVisitied() {
        String key = UserInfoKeeper.getInstance().getPhone() + "last_time";
        editor.putLong(key, System.currentTimeMillis());
    }
}
