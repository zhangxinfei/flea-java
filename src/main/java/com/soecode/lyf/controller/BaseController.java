package com.soecode.lyf.controller;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：zhangxinfei
 * @ Date       ：Created in 17:17 2018/12/2
 */
public class BaseController {

    /**
     * Describe: 分页查询的返回结果
     * @author 张新飞
     * @date 2018/12/17
     * @parms  * @param null
     * @return
     */
    public Map<String, Object> ResultPage(PageInfo<?> pageInfo){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", pageInfo.getList());
        result.put("count", pageInfo.getTotal());
        result.put("code", 0);
        result.put("msg", "请求成功");
        return result;
    }
    public Map<String, Object> ResultPage(List<?> list){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", list);
        result.put("count", list.size());
        result.put("code", 0);
        result.put("msg", "请求成功");
        return result;
    }
}
