package com.soecode.lyf.service;

import com.soecode.lyf.entity.Admin;
import com.soecode.lyf.entity.CommodityType;
import com.soecode.lyf.entity.result.ResultModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface CommodityTypeService {
    /**
     * 查看商品分类的全部分类
     *  @Request
     *  @Parms admin
     */
    ResultModel selectAllType(List<Map<String,Object>> permissionList, String permissionName);

    /**
     *
     * @param admin
     * @return
     */
//    ResultModel selectdeleteType(Admin admin);

    /**
     * 删除商品分类
     * @param commodityType
     * @return
     */
    ResultModel deleteCommodityType(List<Map<String,Object>> permissionList, String permissionName,CommodityType commodityType);

    /**
     * 新增商品分类
     * @param commodityType
     * @return
     */
    ResultModel insertCommodityType(List<Map<String,Object>> permissionList, String permissionName,CommodityType commodityType);

    /**
     * 修改商品分类
     * @param commodityType
     * @return
     */
    ResultModel updateCommodityType(List<Map<String,Object>> permissionList, String permissionName,CommodityType commodityType);

}
