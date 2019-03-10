package com.soecode.lyf.entity;

public class CommodityWithBLOBs extends Commodity {
    private byte[] commodityPhoto;

    private String commodityDescribe;


    public void setCommodityPhoto(byte[] commodityPhoto) {
        this.commodityPhoto = commodityPhoto;
    }

    public String getCommodityDescribe() {
        return commodityDescribe;
    }

    public void setCommodityDescribe(String commodityDescribe) {
        this.commodityDescribe = commodityDescribe == null ? null : commodityDescribe.trim();
    }
}