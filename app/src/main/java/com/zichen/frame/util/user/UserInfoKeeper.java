package com.zichen.frame.util.user;

import android.content.Context;
import android.text.TextUtils;

import com.zichen.frame.constants.ConstantsKey;
import com.zichen.frame.manager.BFPreferenceManager;
import com.zichen.frame.util.CacheUtil;

/**
 * author : lyw
 * time : 2017/9/1
 * desc : 存储用户信息
 * version: 1.0
 */
public class UserInfoKeeper {

    private UserInfo currentUser;

    public void init(UserInfo userInfo) {
        if (null == userInfo) {
            getUserInfoFormCache();
        } else {
            saveUserInfo2Cache(userInfo);
        }
    }

    private void setCurrentUser(UserInfo user) {
        this.currentUser = user;
    }

    /**
     * 取得当前用户
     *
     * @return
     */
    public UserInfo getCurrentUser() {
        return currentUser;
    }

    /**
     * 从Cache文件中查询保存的用户信息
     */
    private void getUserInfoFormCache() {
        UserInfo userInfo = (UserInfo) CacheUtil.getInstance().getAsObject(ConstantsKey.CachKey.USERINFO_FROMCACH);
        if (null != userInfo) {
            setCurrentUser(userInfo);
        }
    }

    /**
     * 保存用户信息到Cache文件中
     */
    private void saveUserInfo2Cache(UserInfo user) {
        //保存新的用户信息到cache文件中
        CacheUtil.getInstance().put(ConstantsKey.CachKey.USERINFO_FROMCACH, user);
        setCurrentUser(user);
    }

    private UserInfoKeeper() {
    }

    private static class UserInfoHolder {
        private static final UserInfoKeeper INSTANCE = new UserInfoKeeper();
    }

    public static UserInfoKeeper getInstance() {
        return UserInfoHolder.INSTANCE;
    }


    /**
     * 用户是否登录-只要sessionId不为空就认为已登录，不考虑session过期的问题
     *
     * @return
     */
    public boolean isLogin() {
        return null != getCurrentUser() && !TextUtils.isEmpty(getCurrentUser().getSession());
    }

    public String getSession() {
        return getCurrentUser().getSession();
    }

    public void setSession(String session) {
        getCurrentUser().setSession(session);
    }

    public String getPhone() {
        if (isLogin()) {
            return getCurrentUser().getUserPhone();
        } else {
            return "";
        }

    }

    public void setPhone(String phone) {
        getCurrentUser().setUserPhone(phone);
    }

    public String getPassword() {
        return getCurrentUser().getUserPassword();
    }

    public void setPassword(String password) {

        getCurrentUser().setUserPassword(password);
    }

    /**
     * 清除用户信息
     *
     * @param context
     */
    public void clearUserInfo(Context context) {

    }

    public void loginOut(Context context) {
        clearUserInfo(context);
        currentUser = null;
    }

    @Override
    public String toString() {
        return "用户信息:\n" +
                "用户名:" + currentUser.getUserName() + "\n" +
                "用户Session:" + currentUser.getSession() + "\n" +
                "用户手机号：" + currentUser.getUserPhone() + "\n";
    }
}
