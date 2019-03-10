package com.soecode.lyf.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
    /**
     * 错误输入日志
     */
    public static final Logger log = LoggerFactory.getLogger(LogUtils.class);

    /**
     * 记录一直 info信息
     *
     * @param message
     */
    public static void logInfo(String message) {
        StringBuilder logInfo = new StringBuilder("======");
        String[] strArray = message.split("->");
        logInfo.append("{key:"+strArray[0]+"}-->>");
        logInfo.append("["+strArray[1]+"]");
        logInfo.append("======");
        log.info(message.toString());
    }

    public static void logInfo(String message, Throwable e) {
        StringBuilder logInfo = new StringBuilder("======");
        String[] strArray = message.split("->");
        logInfo.append("{exception-key:"+strArray[0]+"}-->>");
        logInfo.append("["+strArray[1]+"]");
        logInfo.append("====");
        log.info(logInfo.toString(), e);
    }

    public static void logWarn(String message) {
        StringBuilder s = new StringBuilder();
        s.append((message));

        log.warn(s.toString());
    }

    public static void logWarn(String message, Throwable e) {
        StringBuilder s = new StringBuilder();
        s.append(("exception : -->>"));
        s.append((message));
        log.warn(s.toString(), e);
    }

    public static void logDebug(String message) {
        StringBuilder s = new StringBuilder();
        s.append((message));
        log.debug(s.toString());
    }

    public static void logDebug(String message, Throwable e) {
        StringBuilder s = new StringBuilder();
        s.append(("exception : -->>"));
        s.append((message));
        log.debug(s.toString(), e);
    }

    public static void logError(String message) {
        StringBuilder s = new StringBuilder();
        s.append(message);
        log.error(s.toString());
    }

    /**
     * 记录日志错误信息
     *
     * @param message
     * @param e
     */
    public static void logError(String message, Throwable e) {
        StringBuilder s = new StringBuilder();
        s.append(("exception : -->>"));
        s.append((message));
        log.error(s.toString(), e);
    }

    public static void main(String[] args) {
       /* String a = "[{'level':[{'level':[{'level':[{'level':[{'page_code':'PAGE_0101010101','page_describe':'页面11111','page_id':5,'page_level':'PAGE_005'}],'page_code':'PAGE_0101010100','page_describe':'页面1111','page_id':4,'page_level':'PAGE_004'}],'page_code':'PAGE_0101010000','page_describe':'页面111','page_id':3,'page_level':'PAGE_003'}],'page_code':'PAGE_0101000000','page_describe':'页面11','page_id':2,'page_level':'PAGE_002'}],'page_code':'PAGE_0100000000','page_describe':'页面1','page_id':1,'page_level':'PAGE_001'}]";
        a.replace("}]"," }]:children");
        System.out.print(a);
        logInfo("ccc->测试数据");*/
       /* public static void logInfo(String message) {
            StringBuilder logInfo = new StringBuilder("======");
            String[] strArray = message.split("->");
            logInfo.append("{key:"+strArray[0]+"}-->>");
            logInfo.append("["+strArray[1]+"]");
            logInfo.append("======");
            log.info(message.toString());
        }*/
       /* String formula =  "if_ifif_(#kpi#<#3#if_,#3#if_,#3#if_)";
        String returnStr = "";
        if(formula.contains("if_")){
            String[] formulaArr = formula.split("if_ifif_\\(#");
            for(String ifMula :formulaArr){
                if(ifMula.contains("#if_)")){
                    String[] mulaArr =  ifMula.replace("#if_)","").split("if_,");
                    returnStr+=(StringUtils.isNotBlank(returnStr)?"#":"")+"(#"+mulaArr[0]+"?"+mulaArr[1]+":"+mulaArr[2]+"#)";
                }else{
                    returnStr+=(StringUtils.isNotBlank(returnStr)?"#":"")+ifMula;
                }
            }
            formula = returnStr;
        }
        System.out.print(formula);*/
    }

}