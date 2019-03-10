package com.soecode.lyf.controller;

import com.soecode.lyf.entity.CommodityType;
import com.soecode.lyf.entity.params.Layui;
import com.soecode.lyf.entity.result.ResultModel;
import com.soecode.lyf.enums.WizardAuditEnum;
import com.soecode.lyf.mapper.CommodityTypeMapper;
import com.soecode.lyf.service.CommodityTypeService;
import com.soecode.lyf.util.ResultUtil;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/CommodityType")
@ResponseBody
@CrossOrigin(origins = "http://cxuniversity.top") //允许这个域名就行跨域访问
//@CrossOrigin(origins = "http://127.0.0.1:80") //允许这个域名就行跨域访问
public class CommodityTypeController {
    //日志
    final static Logger logger = LoggerFactory.getLogger(AdministratorsController.class);

    @Autowired
    CommodityTypeService commodityTypeService;
    @Autowired
    CommodityTypeMapper commodityTypeMapper;

    /**
     * 查询商品分类
     * @return
     */
    @RequestMapping(value = "/selectAllType", method = RequestMethod.POST, produces = "application/json")
    public Layui selectByLogin(HttpServletRequest request, @RequestBody String responseData){
        logger.info("查询数据库中分类信息->start");
        net.sf.json.JSONObject  responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String,Object>> permissionList=(List<Map<String,Object>>)JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
        String permissionName = (String) responseList.get("permissionName");
        ResultModel result = commodityTypeService.selectAllType(permissionList,permissionName);
        logger.info("查询数据库中分类信息->end");
        if(result.getData() == ""){
            List layUiData = new ArrayList<>();
            return Layui.data(10,layUiData);
        } else{
            return Layui.data(10, (List<?>)result.getData());
        }
//        return JSON.toJSONString(result, SerializerFeature.WriteMapNullValue);
//        return Layui.data(10, (List<?>)result.getData());
    }

    //    删除商品分类接口
    @RequestMapping(value = "/deleteCommodityType",method = RequestMethod.POST,produces="application/json")
    public ResultModel deleteCommodity(HttpServletRequest request,@RequestBody String responseData) {
        logger.info("删除商品分类->start");
        net.sf.json.JSONObject  responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String,Object>> permissionList=(List<Map<String,Object>>)JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
        int commodityTypeId = (int) responseList.get("commodityTypeId");
        CommodityType commodityType = new CommodityType();
        commodityType.setId(commodityTypeId);
        commodityType.setTypeStatus(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue());
        String permissionName = (String) responseList.get("permissionName");
        ResultModel result = commodityTypeService.deleteCommodityType(permissionList,permissionName,commodityType);
        logger.info("删除商品分类->end");
        return result;
    }


    //新增商品分类接口
    @RequestMapping(value = "/insertCommodityType",method = RequestMethod.POST,produces="application/json")
    public ResultModel insertCommodityType(HttpServletRequest request,@RequestBody String responseData) {
        logger.info("新增商品分类->start");
        net.sf.json.JSONObject  responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String,Object>> permissionList=(List<Map<String,Object>>)JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
//        CommodityType commodityType = (CommodityType)responseList.get("commodityType");
        String permissionName = (String) responseList.get("permissionName");
        CommodityType commodityType = new CommodityType();
        commodityType.setTypeStatus((int)responseList.get("typeStatus"));
        commodityType.setTypeName((String)responseList.get("typeName"));
        commodityType.setCreateData((String)responseList.get("CreateData"));
        commodityType.setCreateBy((String)responseList.get("CreateBy"));
        ResultModel result = commodityTypeService.insertCommodityType(permissionList,permissionName,commodityType);
        logger.info("新增商品分类->end");
        return result;
    }

    //修改商品分类接口
    @RequestMapping(value = "/updateCommodityType",method = RequestMethod.POST,produces="application/json")
    public ResultModel updateCommodityType(HttpServletRequest request,@RequestBody String responseData) {
        logger.info("修改商品分类->start");
        net.sf.json.JSONObject  responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String,Object>> permissionList=(List<Map<String,Object>>)JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
        CommodityType commodityType = new CommodityType();
        commodityType.setId((int)responseList.get("id"));
        commodityType.setUpdateBy((String)responseList.get("updateBy"));
        commodityType.setUpdateBate((String) responseList.get("updateDate"));
        commodityType.setTypeStatus((int)responseList.get("typeStatus"));
        commodityType.setTypeName((String) responseList.get("typeName"));
        String permissionName = (String) responseList.get("permissionName");
        ResultModel result = commodityTypeService.updateCommodityType(permissionList,permissionName,commodityType);
        logger.info("修改商品分类->end");
        return result;
    }


    /**
     * 用户界面
     */

    /**
     * Describe: 下拉菜单，商品分类
     * @author 张新飞
     * @date 2018/12/20
     * @parms  * @param null
     * @return
     */
    @RequestMapping("/Customer/findAllCommodityType")
    public ResultModel findAllCommodityType(){
        logger.info("=============查看全部商品分类信息开始===============");
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("==========查看单个商品信息开始==========");
        List<Map<String,Object>> commodityTypelist = commodityTypeMapper.selectAllByWhere("1=1");
        if(commodityTypelist.size() > 0){
            result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
            result.setData(commodityTypelist);
        }
        logger.info("=============查看全部商品分类信息结束===============");
        return result;
    }

}
