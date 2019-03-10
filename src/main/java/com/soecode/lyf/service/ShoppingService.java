package com.soecode.lyf.service;

import java.util.List;
import java.util.Map;

/**
 * @ Author     ：zhangxinfei
 * @ Date       ：Created in 21:21 2018/12/19
 */
public interface ShoppingService {

    /**
     * 分页查询用户收藏的信息
     * @param where
     * @return
     */
    List<Map<String,Object>> selectShoppingList(String where);
}
