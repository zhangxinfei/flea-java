package com.soecode.lyf.util.entity.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
public class SYAuthTableSonTypeVO {
    private Integer tabletypeIdSon;
    private String tabletypeSon;
    private String tabtypenameSon;
    private String tabletypeurlSon;
    private String tabtypeSon;
    private String levelSon;
    private List<SYAuthTableGrandSonTypeVO> authTableTypeGrandSonList;


    public Integer getTabletypeIdSon() {
        return tabletypeIdSon;
    }

    public void setTabletypeIdSon(Integer tabletypeIdSon) {
        this.tabletypeIdSon = tabletypeIdSon;
    }

    public String getTabletypeSon() {
        return tabletypeSon;
    }

    public void setTabletypeSon(String tabletypeSon) {
        this.tabletypeSon = tabletypeSon;
    }

    public String getTabtypenameSon() {
        return tabtypenameSon;
    }

    public void setTabtypenameSon(String tabtypenameSon) {
        this.tabtypenameSon = tabtypenameSon;
    }

    public String getTabletypeurlSon() {
        return tabletypeurlSon;
    }

    public void setTabletypeurlSon(String tabletypeurlSon) {
        this.tabletypeurlSon = tabletypeurlSon;
    }

    public String getTabtypeSon() {
        return tabtypeSon;
    }

    public void setTabtypeSon(String tabtypeSon) {
        this.tabtypeSon = tabtypeSon;
    }

    public String getLevelSon() {
        return levelSon;
    }

    public void setLevelSon(String levelSon) {
        this.levelSon = levelSon;
    }

    public List<SYAuthTableGrandSonTypeVO> getAuthTableTypeGrandSonList() {
        return authTableTypeGrandSonList;
    }

    public void setAuthTableTypeGrandSonList(List<SYAuthTableGrandSonTypeVO> authTableTypeGrandSonList) {
        this.authTableTypeGrandSonList = authTableTypeGrandSonList;
    }
}
