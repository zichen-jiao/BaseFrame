package com.zichen.frame.entity.request;

import java.io.Serializable;

/**
 * 获取banner列表.客户端请求
 *
 * @author 代码生成器v1.0
 */
public class GetBannerListRequest extends MobileRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String equipmentType;

    private String equipmentId;

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Override
    public String toString() {
        return "GetBannerListRequest{" +
                "equipmentType='" + equipmentType + '\'' +
                ", equipmentId='" + equipmentId + '\'' +
                "} " + super.toString();
    }
}