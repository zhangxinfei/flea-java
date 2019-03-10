package com.soecode.lyf.util.aop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by wxl on 2017/7/25.
 */
@Component("configInfo")
public class ConfigInfo {

    @Value("${jdbc.url}")
    private String jdbcUrl;

//    @Value("${market.url}")
//    private String marketUrl;

//    public String getMarketUrl() {
//        return marketUrl;
//    }
//
//    public void setMarketUrl(String marketUrl) {
//        this.marketUrl = marketUrl;
//    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }
}
