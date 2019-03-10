package com.soecode.lyf.mapper;

import com.soecode.lyf.entity.CommodityType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommodityTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommodityType record);

    int insertSelective(CommodityType record);

    CommodityType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommodityType record);

    int updateByPrimaryKey(CommodityType record);

    /**
     * 查询全部商品分类
     *
     */
    List<Map<String,Object>> selectAllType();

    /**
     * 删除商品分类
     */
    int deleteCommodityType(CommodityType commodityType);

    /**
     * 新增商品分类
     * @param commodityType
     * @return
     */
    int insertCommodityType(CommodityType commodityType);

    /**
     * 修改商品分类
     * @param commodityType
     * @return
     */
    int updateCommodityType(CommodityType commodityType);

    /**
     * 通过id和typeid查询
     * @param commodityList
     * @return
     */
    List<Map<String,Object>> selectAllCommodityTypeById(Map<String,Object> commodityList);

    /**
     * 查看全部信息
     * @param where
     * @return
     */
    List<Map<String,Object>> selectAllByWhere(@Param("where") String where);

}