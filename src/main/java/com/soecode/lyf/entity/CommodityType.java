package com.soecode.lyf.entity;

public class CommodityType {
    private Integer id;

    private String typeName;

    private Integer typeFatherId;

    private Integer typeStatus;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public Integer getTypeFatherId() {
        return typeFatherId;
    }

    public void setTypeFatherId(Integer typeFatherId) {
        this.typeFatherId = typeFatherId;
    }

    public Integer getTypeStatus() {
        return typeStatus;
    }

    public void setTypeStatus(Integer typeStatus) {
        this.typeStatus = typeStatus;
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