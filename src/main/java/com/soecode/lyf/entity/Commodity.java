package com.soecode.lyf.entity;

import java.math.BigDecimal;

public class Commodity {
    private String commodityDescribe;

    public String getCommodityDescribe() {
        return commodityDescribe;
    }

    public void setCommodityDescribe(String commodityDescribe) {
        this.commodityDescribe = commodityDescribe;
    }

    private String imgUrl1;
    private String imgUrl2;

    public String getImgUrl1() {
        return imgUrl1;
    }

    public void setImgUrl1(String imgUrl1) {
        this.imgUrl1 = imgUrl1;
    }

    public String getImgUrl2() {
        return imgUrl2;
    }

    public void setImgUrl2(String imgUrl2) {
        this.imgUrl2 = imgUrl2;
    }

    public String getImgUrl3() {
        return imgUrl3;
    }

    public void setImgUrl3(String imgUrl3) {
        this.imgUrl3 = imgUrl3;
    }

    private String imgUrl3;
    private String commodityPhoto;

    public String getCommodityPhoto() {
        return commodityPhoto;
    }

    public void setCommodityPhoto(String commodityPhoto) {
        this.commodityPhoto = commodityPhoto;
    }

    private Integer id;

    private String commodityName;

    private Integer commodityPrice;

    private String commodityAddress;

    private BigDecimal commodityCost;

    private Integer typeId;

    private String userIphone;

    private String userQq;

    private Integer commodityStatus;

    private String createData;

    private String createBy;

    private String updateBate;

    private String updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public Integer getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Integer commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getCommodityAddress() {
        return commodityAddress;
    }

    public void setCommodityAddress(String commodityAddress) {
        this.commodityAddress = commodityAddress == null ? null : commodityAddress.trim();
    }

    public BigDecimal getCommodityCost() {
        return commodityCost;
    }

    public void setCommodityCost(BigDecimal commodityCost) {
        this.commodityCost = commodityCost;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getUserIphone() {
        return userIphone;
    }

    public void setUserIphone(String userIphone) {
        this.userIphone = userIphone == null ? null : userIphone.trim();
    }

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq == null ? null : userQq.trim();
    }

    public Integer getCommodityStatus() {
        return commodityStatus;
    }

    public void setCommodityStatus(Integer commodityStatus) {
        this.commodityStatus = commodityStatus;
    }

    public String getCreateData() {
        return createData;
    }

    public void setCreateData(String createData) {
        this.createData = createData == null ? null : createData.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getUpdateBate() {
        return updateBate;
    }

    public void setUpdateBate(String updateBate) {
        this.updateBate = updateBate == null ? null : updateBate.trim();
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }
}