package com.soecode.lyf.mapper;

import com.soecode.lyf.entity.Commodity;
import com.soecode.lyf.entity.CommodityWithBLOBs;
import com.soecode.lyf.util.pageUtil.CrudDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface CommodityMapper extends CrudDao<Commodity> {
    int deleteByPrimaryKey(Integer id);

    int insert(CommodityWithBLOBs record);

    int insertSelective(CommodityWithBLOBs record);

    Commodity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommodityWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CommodityWithBLOBs record);

    int updateByPrimaryKey(Commodity record);

    /**
     * 查询用户和管理员信息
     * @return
     */
    List<Map<String,Object>> selectAllCommodity();

    /**
     * 删除用户和商品信息
     * @param commodity
     * @return
     */
    int deleteCommodity(Commodity commodity);

    /**
     * 获取总记录数
     * @param map
     * @return
     */
    int getCount(Map<String,Object> map);

    /**
     * 分页查询全部信息
     * @param map
     * @return
     */
    List<Map<String,Object>> findByPage(Map<String,Object> map);

    /**
     * 分页查询商品信息
     * @param where
     * @return
     */
    List<Map<String,Object>> selectCommodityList(@Param("where")String where);

    /**
     * 发布商品
     * @param commodity
     * @return
     */
    int insertCommodity(Commodity commodity);

    /**
     *  查询我的收藏
     * @param loveMap
     * @return
     */
    List<Map<String,Object>> selectLoveByList(Map<String,Object> loveMap);

}