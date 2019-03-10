package com.soecode.lyf.service.impl;

import com.soecode.lyf.common.commonMethod.CrudService;
import com.soecode.lyf.entity.Shopping;
import com.soecode.lyf.mapper.ShoppingMapper;
import com.soecode.lyf.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *
 *  @ Author ：zhangxinfei
 *  @ Date   ：Created in 21:22 2018/12/19
 */
@Service
@Transactional
public class ShoppingServiceImpl extends CrudService<ShoppingMapper,Shopping> implements ShoppingService {

    @Autowired
    ShoppingMapper shoppingMapper;

    @Override
    public List<Map<String, Object>> selectShoppingList(String where) {
        return shoppingMapper.selectShoppingList(where);
    }
}
