package com.soecode.lyf.service.impl;

import com.soecode.lyf.entity.Admin;
import com.soecode.lyf.entity.CommodityType;
import com.soecode.lyf.entity.Permission;
import com.soecode.lyf.entity.params.AdminParams;
import com.soecode.lyf.entity.result.ResultModel;
import com.soecode.lyf.enums.WizardAuditEnum;
import com.soecode.lyf.mapper.AdminMapper;
import com.soecode.lyf.mapper.CommodityTypeMapper;
import com.soecode.lyf.mapper.PermissionMapper;
import com.soecode.lyf.mapper.RolePermissionMapper;
import com.soecode.lyf.service.CommodityTypeService;
import com.soecode.lyf.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CommodityTypeServiceImpl implements CommodityTypeService {
    //    日志
    final static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    CommodityTypeMapper commodityTypeMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    PermissionMapper permissionMapper;


    /**
     * 查询全部商品
     * @return
     */
    @Override
    public ResultModel selectAllType(List<Map<String,Object>> permissionList, String permissionName ) {
        //初始化结果集
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限->start");
        for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                logger.info("查询全部商品分类->start");
                List<Map<String,Object>> commodityTypeList = commodityTypeMapper.selectAllType();
                if(commodityTypeList.size() > 0){
                    for (Map commodityTypeMap: commodityTypeList) {
                        if((int)commodityTypeMap.get("typeStatus") == 10){
                            commodityTypeMap.put("statusCode",WizardAuditEnum.existenceStatusEnum.STATUS_SUCCESS.getDesc());
                        }
                        else {
                            commodityTypeMap.put("statusCode",WizardAuditEnum.existenceStatusEnum.STATUS_FAIL.getDesc());
                        }
                    }
                    result.setSuccess(true);
                    result.setData(commodityTypeList);
                }
                logger.info("查询全部商品分类->end");
            }
        }
        return result;
    }

    /**
     * 删除商品分类
     * @param permissionList
     * @param permissionName
     * @param commodityType
     * @return
     */
    @Override
    public ResultModel deleteCommodityType(List<Map<String,Object>> permissionList, String permissionName,CommodityType commodityType) {
        //初始化结果集
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限->start");
        A:       for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                int deleteAdminInfluence = commodityTypeMapper.deleteCommodityType(commodityType);
                if(deleteAdminInfluence > 0){
                    result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
                    break A;
                }
                logger.info("验证权限->end");
            }
        }
        return result;
    }

    //新增商品分类
    @Override
    public ResultModel insertCommodityType(List<Map<String,Object>> permissionList, String permissionName,CommodityType commodityType) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限->start");
        A:       for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                int deleteAdminInfluence = commodityTypeMapper.insertCommodityType(commodityType);
                if(deleteAdminInfluence > 0){
                    result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
                    break A;
                }
                logger.info("验证权限->end");
            }
        }
        return result;
    }

    //修改商品分类
    @Override
    public ResultModel updateCommodityType(List<Map<String,Object>> permissionList, String permissionName,CommodityType commodityType) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限->start");
        A:       for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                int deleteAdminInfluence = commodityTypeMapper.updateCommodityType(commodityType);
                if(deleteAdminInfluence > 0){
                    result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
                    break A;
                }
                logger.info("验证权限->end");
            }
        }
        return result;
    }
}
