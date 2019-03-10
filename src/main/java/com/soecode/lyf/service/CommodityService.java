package com.soecode.lyf.service;

import com.soecode.lyf.entity.Commodity;
import com.soecode.lyf.entity.result.ResultModel;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface CommodityService {
    /**
     * 查询用户和管理员信息
     * @param permissionList
     * @param permissionName
     * @return
     */
    ResultModel selectAllCommodity(List<Map<String,Object>> permissionList, String permissionName);

    /**
     * 删除用户和商品信息
     * @param permissionList
     * @param permissionName
     * @param commodity
     * @return
     */
    ResultModel deleteCommodity(List<Map<String,Object>> permissionList, String permissionName,Commodity commodity);

    /**
     * 分页查询全部商品信息
     * @param where
     * @return
     */
    List<Map<String,Object>> selectCommodityList(String where);


    /**
     * 分页查询全部信息
     * @param map
     * @return
     */
    List<Map<String,Object>> findByPage(Map<String,Object> map);

    /**
     * 发布商品
     * @param commodity
     * @return
     */
    int insertCommodity(Commodity commodity);

    Commodity selectByPrimaryKey(Integer id);

    ResultModel uploadImages(HttpServletRequest request, @RequestParam(value = "file") MultipartFile[] files,String createData);
}
