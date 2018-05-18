package com.zichen.frame.util.user;

import java.io.Serializable;

public interface UserInfo extends Serializable {

    /**
     * 取得用户Session
     *
     * @return
     */
    String getSession();

    /**
     * 取得用户姓名
     *
     * @return
     */
    String getUserName();

    /**
     * 取得用户手机号
     *
     * @return
     */
    String getUserPhone();

    /**
     * 取得用户密码
     *
     * @return
     */
    String getUserPassword();

    /**
     * 设置用户Session
     *
     * @param session
     */
    void setSession(String session);

    /**
     * 设置用户姓名
     *
     * @param userName
     */
    void setUserName(String userName);

    /**
     * 设置用户手机号
     *
     * @param userPhone
     */
    void setUserPhone(String userPhone);

    /**
     * 设置用户密码
     *
     * @param userPassword
     */
    void setUserPassword(String userPassword);

}
