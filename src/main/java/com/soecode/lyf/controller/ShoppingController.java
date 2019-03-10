package com.soecode.lyf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soecode.lyf.entity.Shopping;
import com.soecode.lyf.entity.result.ResultModel;
import com.soecode.lyf.enums.WizardAuditEnum;
import com.soecode.lyf.mapper.CommodityMapper;
import com.soecode.lyf.mapper.PictureMapper;
import com.soecode.lyf.mapper.ShoppingMapper;
import com.soecode.lyf.service.ShoppingService;
import com.soecode.lyf.util.ResultUtil;
import com.soecode.lyf.util.StringUtils;
import com.soecode.lyf.util.pageUtil.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *  @ Author ：zhangxinfei
 *  @ Date   ：Created in 21:24 2018/12/19
 */
@Controller
@RequestMapping("/Shopping")
@ResponseBody
@CrossOrigin(origins = "http://cxuniversity.top") //允许这个域名就行跨域访问
public class ShoppingController extends BaseController {
    //日志
    final static Logger logger = LoggerFactory.getLogger(AdministratorsController.class);

    @Autowired
    ShoppingService shoppingService;
    @Autowired
    ShoppingMapper shoppingMapper;
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    PictureMapper pictureMapper;

    /**
     * Describe: 查看当前用户的所有收藏信息
     * @author 张新飞
     * @date 2018/12/19
     * @parms  * @param null
     * @return
     */
    @RequestMapping("/findAllShopping")
    public Map<String,Object> findAllShopping(PageUtil pageUtil,String username){
        logger.info("===========分页查询收藏商品信息开始============");
        String where = "userIphone = "+ "'"+username+"'";
        String orderStr =" id ";
        if (StringUtils.isNotEmpty(pageUtil.getSort()) && StringUtils.isNotEmpty(pageUtil.getOrder())) {
            orderStr = pageUtil.getSort() + " " + pageUtil.getOrder();
        }
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getLimit(),orderStr);
        //按页查询数据库
        List<Map<String,Object>> list = shoppingService.selectShoppingList(where);
//        List<Map<String,Object>> commodityList = new ArrayList<>();
        if(list.size() != 0){
            Map<String,Object> loveMap = new HashMap<>();
            for (int i=0;i<list.size();i++){
                list.get(i).put("id",list.get(i).get("commodityId"));
            }
            loveMap.put("list",list);
            list = commodityMapper.selectLoveByList(loveMap);
            int count;
            for (Map<String, Object> pictureIDMap : list) {
                //重置count
                count = 1;
                //通过查询出来的商品照片的id去找到图片的路径并存入list集合
                String pictureWhere = "ID = " + "'" + pictureIDMap.get("commodityPhoto") + "'";
                List<Map<String, Object>> pictureList = pictureMapper.selectPictureByWhere(pictureWhere);
                for (Map<String, Object> pictureUrlMap : pictureList) {
                    pictureIDMap.put("picture" + Integer.toString(count), pictureUrlMap.get("imgUrl"));
                    count++;
                }
            }
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
            return ResultPage(pageInfo);
        }
        logger.info("===========分页查询收藏商品信息结束============");
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String,Object>>(list);
        return ResultPage(pageInfo);    }

    /**
     * Describe: 删除收藏
     * @author 张新飞
     * @date 2018/12/22
     * @parms  * @param null
     * @return
     */
    @RequestMapping("/deleteShopping")
    public ResultModel deleteShopping(int id){
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("=============删除收藏信息开始===============");
        int Influence = shoppingMapper.deleteByPrimaryKey(id);
        if(Influence > 0){
            result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
        }
        logger.info("==============删除收藏信息结束 =============");
        return result;
    }

    /**
     * Describe: 新增收藏
     * @author 张新飞
     * @date 2018/12/22
     * @parms  * @param null
     * @return
     */
    @RequestMapping(value = "/collectionShopping",method = RequestMethod.POST)
    public ResultModel collectionShopping(@RequestBody Shopping shopping){
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("=============新增收藏信息开始===============");
        int Influence = shoppingMapper.insertAll(shopping);
        if(Influence > 0){
            result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
        }
        logger.info("==============新增收藏信息结束 =============");
        return result;
    }

    /**
     * Describe: 收藏信息查重
     * @author 张新飞
     * @date 2018/12/23
     * @parms  * @param null
     * @return
     */
    @RequestMapping(value = "/checkShopping",method = RequestMethod.POST)
    public ResultModel checkShopping(@RequestBody Shopping shopping){
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("=============检查收藏信息是否重复开始===============");
        String where = "commodityId = "+shopping.getCommodityId()+" and userIphone ="+"'"+shopping.getUserIphone()+"'";
        List<Map<String,Object>> Influence = shoppingMapper.selectShoppingList(where);
        if(Influence.size() == 0){
            result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
        }
        logger.info("==============检查收藏信息是否重复结束 =============");
        return result;
    }

}
