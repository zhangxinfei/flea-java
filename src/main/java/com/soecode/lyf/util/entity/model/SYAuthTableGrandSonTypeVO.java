package com.soecode.lyf.util.entity.model;

import java.util.List;

/**
 * Created by Administrator on 2017/6/28.
 */
public class SYAuthTableGrandSonTypeVO {
    private Integer tabletypeGrandIdson;
    private String tabletypeGrandson;
    private String tabtypenameGrandson;
    private String tabletypeurlGrandson;
    private String tabtypeGrandson;
    private String levelGrandson;
    private List<SYAuthTableHeavyGrandonVO> authTableHeavyGrandonList;

    public List<SYAuthTableHeavyGrandonVO> getAuthTableHeavyGrandonList() {
        return authTableHeavyGrandonList;
    }

    public void setAuthTableHeavyGrandonList(List<SYAuthTableHeavyGrandonVO> authTableHeavyGrandonList) {
        this.authTableHeavyGrandonList = authTableHeavyGrandonList;
    }

    public Integer getTabletypeGrandIdson() {
        return tabletypeGrandIdson;
    }

    public void setTabletypeGrandIdson(Integer tabletypeGrandIdson) {
        this.tabletypeGrandIdson = tabletypeGrandIdson;
    }

    public String getTabletypeGrandson() {
        return tabletypeGrandson;
    }

    public void setTabletypeGrandson(String tabletypeGrandson) {
        this.tabletypeGrandson = tabletypeGrandson;
    }

    public String getTabtypenameGrandson() {
        return tabtypenameGrandson;
    }

    public void setTabtypenameGrandson(String tabtypenameGrandson) {
        this.tabtypenameGrandson = tabtypenameGrandson;
    }

    public String getTabletypeurlGrandson() {
        return tabletypeurlGrandson;
    }

    public void setTabletypeurlGrandson(String tabletypeurlGrandson) {
        this.tabletypeurlGrandson = tabletypeurlGrandson;
    }

    public String getTabtypeGrandson() {
        return tabtypeGrandson;
    }

    public void setTabtypeGrandson(String tabtypeGrandson) {
        this.tabtypeGrandson = tabtypeGrandson;
    }

    public String getLevelGrandson() {
        return levelGrandson;
    }

    public void setLevelGrandson(String levelGrandson) {
        this.levelGrandson = levelGrandson;
    }
}
