package com.soecode.lyf.mapper;

import com.soecode.lyf.entity.Picture;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PictureMapper {
    int insert(Picture record);

    int insertSelective(Picture record);

    /**
     * 批量上传照片
     * @param list
     * @return
     */
    int insertPictureList(Map<String,Object> list);

    /**
     * 批量查询照片
     * @param where
     * @return
     */
    List<Map<String,Object>> selectPictureByWhere(@Param("where") String where);

    /**
     * 删除照片
     * @param pictureID
     * @return
     */
    int deletePicture(String pictureID);

}