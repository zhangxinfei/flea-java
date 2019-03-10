package com.soecode.lyf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soecode.lyf.entity.Commodity;
import com.soecode.lyf.entity.params.Layui;
import com.soecode.lyf.entity.result.ResultModel;
import com.soecode.lyf.enums.WizardAuditEnum;
import com.soecode.lyf.mapper.CommodityMapper;
import com.soecode.lyf.mapper.PictureMapper;
import com.soecode.lyf.service.CommodityService;
import com.soecode.lyf.util.ResultUtil;
import com.soecode.lyf.util.StringUtils;
import com.soecode.lyf.util.pageUtil.PageUtil;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/Commodity")
@ResponseBody
@CrossOrigin(origins = "http://cxuniversity.top:8010") //允许这个域名就行跨域访问
//@CrossOrigin(origins = "http://127.0.0.1:80") //允许这个域名就行跨域访问
public class CommodityController extends BaseController {
    //日志
    final static Logger logger = LoggerFactory.getLogger(AdministratorsController.class);

    @Autowired
    CommodityService commodityService;
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    PictureMapper pictureMapper;

    //查看用户和商品信息
    @RequestMapping(value = "/selectAllCommodity", method = RequestMethod.POST, produces = "application/json")
    public Layui selectAllCommodity(HttpServletRequest request, @RequestBody String responseData) {
        logger.info("查询数据库中分类信息->start");
        net.sf.json.JSONObject responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String, Object>> permissionList = (List<Map<String, Object>>) JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
        String permissionName = (String) responseList.get("permissionName");
        ResultModel result = commodityService.selectAllCommodity(permissionList, permissionName);
        logger.info("查询数据库中分类信息->end");
        if (result.getData() == "") {
            List layUiData = new ArrayList<>();
            return Layui.data(10, layUiData);
        } else {
            return Layui.data(10, (List<?>) result.getData());
        }
    }

    //删除用户和商品信息
    @RequestMapping(value = "/deleteCommodity", method = RequestMethod.POST, produces = "application/json")
    public ResultModel deleteCommodity(HttpServletRequest request, @RequestBody String responseData) {
        logger.info("查询数据库中分类信息->start");
        net.sf.json.JSONObject responseList = net.sf.json.JSONObject.fromObject(responseData);
        List<Map<String, Object>> permissionList = (List<Map<String, Object>>) JSONArray.toList(JSONArray.fromObject(responseList.get("permissionList")), Map.class);
        String permissionName = (String) responseList.get("permissionName");
        Commodity commodity = new Commodity();
        commodity.setId((int) responseList.get("id"));
        commodity.setCommodityStatus(WizardAuditEnum.existenceStatusEnum.STATUS_FAIL.getValue());
        commodity.setUpdateBate((String) responseList.get("updateBate"));
        commodity.setUpdateBy((String) responseList.get("updateBy"));
        ResultModel result = commodityService.deleteCommodity(permissionList, permissionName, commodity);
        logger.info("查询数据库中分类信息->end");
        return result;
    }

    /**
     * Describe: 分页查询商品信息
     * @return
     * @author 张新飞
     * @date 2018/12/19
     * @parms * @param null
     */
    @RequestMapping(value = "/findCommodityByPage")
    public Map<String, Object> findCommodityByPage(PageUtil pageUtil,
                                                   @RequestParam(value = "cId", required = false) String cId
    ) {
        logger.info("===========分页查询商品信息开始============");
        String where = " commodityStatus = 10";
        String orderStr = " id desc";
        if (StringUtils.isNotEmpty(pageUtil.getSort()) && StringUtils.isNotEmpty(pageUtil.getOrder())) {
            orderStr = pageUtil.getSort() + " " + pageUtil.getOrder();
        }
        if (cId != null && !("").equals(cId)) {
            where += " and typeId =" + Integer.valueOf(cId);
        }
        PageHelper.startPage(pageUtil.getPage(), pageUtil.getLimit(), orderStr);
        //按页查询数据库
        List<Map<String, Object>> list = commodityService.selectCommodityList(where);
        int count;
        if(list.size() != 0){
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
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
            return ResultPage(pageInfo);
        }
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
        logger.info("===========分页查询商品信息结束============");
        return ResultPage(pageInfo);
    }

    /**
     * Describe: 查看单个商品信息
     *
     * @return
     * @author 张新飞
     * @date 2018/12/19
     * @parms * @param null
     */
    @RequestMapping(value = "/findOneCommodity")
    public ResultModel findOneCommodity(int id) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(), WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("==========查看单个商品信息开始==========");
        String where = "id = " + id;
        List<Map<String, Object>> commodityList = commodityMapper.selectCommodityList(where);
        if (commodityList.size() > 0) {
            int count;
            for (Map<String, Object> pictureIDMap : commodityList) {
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
            result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(), WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
            result.setData(commodityList);
        }
        logger.info("==========查看单个商品信息结束==========");
        return result;
    }

    /**
     * Describe: 发布商品
     * @return
     * @author 张新飞
     * @date 2018/12/19
     * @parms * @param null
     */
    @RequestMapping(value = "/Customer/releaseCommodity", method = RequestMethod.POST)
    public ResultModel releaseCommodity(String commodityAddress,String commodityCost,
                                        String commodityDescribe,String commodityName,String commodityPhoto,String commodityPrice,
                                        String createBy,String createData,String typeId,String userIphone,String userQq
            , HttpServletRequest request, @RequestParam(value = "file") MultipartFile[] files) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(), WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("==========发布商品开始===========");
        Commodity commodity = new Commodity();
        commodity.setCommodityAddress(commodityAddress);
        commodity.setCommodityCost(BigDecimal.valueOf(Integer.valueOf(commodityCost)));
        commodity.setCommodityDescribe(commodityDescribe);
        commodity.setCommodityName(commodityName);
        commodity.setCommodityPhoto(commodityPhoto);
        commodity.setCommodityPrice(Integer.valueOf(commodityPrice));
        commodity.setCreateBy(createBy);
        commodity.setCreateData(createData);
        commodity.setTypeId(Integer.valueOf(typeId));
        commodity.setUserIphone(userIphone);
        commodity.setUserQq(userQq);
        commodity.setCommodityStatus(10);
        result = commodityService.uploadImages(request,files,commodity.getCommodityPhoto());
        if(result.getCode() == 10){
            int Influence = commodityService.insertCommodity(commodity);
            if (Influence > 0) {
                result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(), WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
            }
        }
        logger.info("==========发布商品结束===========");
        return result;
    }

    /**
     * 提交图片返回路径
     * Describe:
     * @return
     * @author 张新飞
     * @date 2018/12/22
     * @parms * @param null
     */
//    @RequestMapping(value = "/uploadImages", method = RequestMethod.POST)
//    public ResultModel uploadImages(HttpServletRequest request, @RequestParam(value = "file") MultipartFile[] files, String createData) throws Exception {
//        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(), WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
//        logger.info("================上传照片开始================");
//        //定义序号    
//        int count = 1;
//        //定义集合，最多存3张照片，批量存入数据库
//        List<Picture> pictureList = new ArrayList<>();
//        //当前项目路径（存储图片的文件夹）
//        String path = request.getSession().getServletContext().getRealPath("/image");
//        File fileUrl = new File(path);
//        //如果当前项目里不存在images文件夹，就创建
//        if (!fileUrl.exists()) {
//            fileUrl.mkdir();
//        }
//        System.out.println(files.length);
//        //定义一个当前商品的统一id,把当前时间作为同一商品的统一ID
//        String pictureID = createData;
//        for (MultipartFile img : files) {
//            //最多只能上传3张图片
//            if (count <= 3) {
//                if (!img.isEmpty()) {
//                    //生成一个唯一标识符给图片命名，避免图片名重复，覆盖原有图片            
//                    String name = UUID.randomUUID().toString().replaceAll("-", "");
//                    // 文件的扩展名
//                    //img.getOriginalFilename() 是上传图片的原始名字
//                    String ext = FilenameUtils.getExtension(img.getOriginalFilename());
//                    //transferTo()方法将上传的文件写到服务器指定的文件
//                    img.transferTo(new File(path + "/" + name + "." + ext));
//                    // 存到数据库的路径（相对路径）
//                    String url = "image/" + name + "." + ext;
//                    System.out.println("数据库路径：" + url);
//                    Picture picture = new Picture();
//                    picture.setID(pictureID);
//                    picture.setImgUrl(url);
//                    pictureList.add(picture);
//                    count++;
//                }
//            }
//        }
//        Map<String, Object> pictureMap = new HashMap<>();
//        pictureMap.put("pictureList", pictureList);
//        //插入数据库
//        int Influence = pictureMapper.insertPictureList(pictureMap);
//        if (Influence > 0) {
//            result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(), WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
//            //把商品照片的统一ID返回
//            result.setData(pictureID);
//        }
//        logger.info("================上传照片结束================");
//        return result;
//    }

    /**
     * Describe: 删除照片
     *
     * @return
     * @author 张新飞
     * @date 2018/12/22
     * @parms * @param null
     */
    @RequestMapping("/Customer/deletePicture")
    public ResultModel deletePicture(String pictureID) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(), WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("=================删除照片开始=================");
        int Influence = pictureMapper.deletePicture(pictureID);
        if (Influence > 0) {
            result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(), WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
        }
        logger.info("=================删除照片结束=================");
        return result;
    }

    /**
     * Describe: 我的收藏
     *
     * @return
     * @author 张新飞
     * @date 2018/12/22
     * @parms * @param null
     */
    @RequestMapping(value = "/Customer/myLove", method = RequestMethod.POST)
    public Map<String, Object> myLove(PageUtil pageUtil, @RequestBody String username) {
        logger.info("===========我的收藏商品信息开始============");
        String where = " userIphone = " + username;
        String orderStr = " id ";
        if (StringUtils.isNotEmpty(pageUtil.getSort()) && StringUtils.isNotEmpty(pageUtil.getOrder())) {
            orderStr = pageUtil.getSort() + " " + pageUtil.getOrder();
        }
        PageHelper.startPage(pageUtil.getPage(), pageUtil.getLimit(), orderStr);
        //按页查询数据库
        List<Map<String, Object>> list = commodityMapper.selectCommodityList(where);
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
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
        logger.info("===========我的收藏商品信息结束============");
        return ResultPage(pageInfo);
    }

    /**
     * Describe: 模糊查询商品信息
     *
     * @return
     * @author 张新飞
     * @date 2018/12/19
     * @parms * @param null
     */
    @RequestMapping(value = "/findCommodityBymohu", method = RequestMethod.GET)
    public Map<String, Object> findCommodityBymohu(PageUtil pageUtil,String commodityName) {
        logger.info("===========模糊查询商品信息开始============");
        String where = " commodityStatus = 10 and commodityName like '%"+commodityName+"%'";
        String orderStr = " id desc";
        if (StringUtils.isNotEmpty(pageUtil.getSort()) && StringUtils.isNotEmpty(pageUtil.getOrder())) {
            orderStr = pageUtil.getSort() + " " + pageUtil.getOrder();
        }
        PageHelper.startPage(pageUtil.getPage(), pageUtil.getLimit(), orderStr);
        //按页查询数据库
        List<Map<String, Object>> list = commodityService.selectCommodityList(where);
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
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
        logger.info("===========模糊查询商品信息结束============");
        return ResultPage(pageInfo);
    }

    /**
     * Describe: 我发布的商品
     * @author 张新飞
     * @date 2018/12/23
     * @parms  * @param null
     * @return
     */
    @RequestMapping(value = "/Customer/findMyFabu")
    public Map<String, Object> findMyFabu(PageUtil pageUtil,String username) {
        logger.info("===========我发布的商品信息开始============");
        String where = " userIphone = '" + username+"' and commodityStatus = 10";
        String orderStr = " id ";
        if (StringUtils.isNotEmpty(pageUtil.getSort()) && StringUtils.isNotEmpty(pageUtil.getOrder())) {
            orderStr = pageUtil.getSort() + " " + pageUtil.getOrder();
        }
        PageHelper.startPage(pageUtil.getPage(), pageUtil.getLimit(), orderStr);
        //按页查询数据库
        List<Map<String, Object>> list = commodityMapper.selectCommodityList(where);
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
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
        logger.info("===========我发布的商品信息结束============");
        return ResultPage(pageInfo);
    }
}


