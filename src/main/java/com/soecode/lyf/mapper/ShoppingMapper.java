package com.soecode.lyf.mapper;

import com.soecode.lyf.common.commonMethod.CrudDao;
import com.soecode.lyf.entity.Shopping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ShoppingMapper extends CrudDao<Shopping> {
    int deleteByPrimaryKey(Integer id);

    int insert(Shopping record);

    int insertSelective(Shopping record);

    Shopping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shopping record);

    int updateByPrimaryKey(Shopping record);

    /**
     * 分页查询当前用户的收藏信息
     * @param where
     * @return
     */
    List<Map<String, Object>> selectShoppingList(@Param("where") String where);

    /**
     * 新增收藏信息
     * @param shopping
     * @return
     */
    int insertAll(Shopping shopping);

}