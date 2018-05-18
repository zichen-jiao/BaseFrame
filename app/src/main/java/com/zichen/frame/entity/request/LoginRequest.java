package com.zichen.frame.entity.request;

/**
 * 用户登录.客户端请求
 */
public class LoginRequest extends MobileRequest {

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 极光注册id
     */
    private String registrationId;

    /**
     * 设备id
     */
    private String equipmentId;

    /**
     * 设备品牌
     */
    private String equipmentBrand;

    /**
     * 设备型号
     */
    private String equipmentVersion;

    /**
     * 客户端版本号
     */
    private String clientVersion;

    /**
     * 客户端类型(andriod/ios)
     */
    private String equipmentType;

    public LoginRequest() {
        super();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentBrand() {
        return equipmentBrand;
    }

    public void setEquipmentBrand(String equipmentBrand) {
        this.equipmentBrand = equipmentBrand;
    }

    public String getEquipmentVersion() {
        return equipmentVersion;
    }

    public void setEquipmentVersion(String equipmentVersion) {
        this.equipmentVersion = equipmentVersion;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", registrationId='" + registrationId + '\'' +
                ", equipmentId='" + equipmentId + '\'' +
                ", equipmentBrand='" + equipmentBrand + '\'' +
                ", equipmentVersion='" + equipmentVersion + '\'' +
                ", clientVersion='" + clientVersion + '\'' +
                ", equipmentType='" + equipmentType + '\'' +
                "} " + super.toString();
    }
}