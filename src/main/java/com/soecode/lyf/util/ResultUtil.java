package com.soecode.lyf.util;

import com.soecode.lyf.entity.result.ResultModel;

public class ResultUtil {
//    获取失败的常量
    public static ResultModel error(Integer code, String desc){
        return  createData(code,desc,false);
    }
//    储存初始化的常量
    public static ResultModel info(Integer code,String desc){
        return  createData(code,desc,false);
    }
//    获取成功的常量
    public static ResultModel success(Integer code,String desc){
        return createData(code,desc,true);
    }
//    获取持久化的所有数据
    private static ResultModel createData(Integer code,String desc,boolean isTrue){
        ResultModel result = new ResultModel();
        result.setCode(code);
        result.setMessage(desc);
        result.setSuccess(isTrue);
        result.setData("");
        return  result;
    }
}
