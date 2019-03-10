package com.soecode.lyf.service.impl;


import com.auth0.jwt.internal.org.apache.commons.io.FilenameUtils;
import com.soecode.lyf.entity.Commodity;
import com.soecode.lyf.entity.Picture;
import com.soecode.lyf.entity.result.ResultModel;
import com.soecode.lyf.enums.WizardAuditEnum;
import com.soecode.lyf.mapper.CommodityMapper;
import com.soecode.lyf.mapper.CommodityTypeMapper;
import com.soecode.lyf.mapper.PictureMapper;
import com.soecode.lyf.service.CommodityService;
import com.soecode.lyf.util.ResultUtil;
import com.soecode.lyf.util.imagesUtil.ImageCompress;
import com.soecode.lyf.util.pageUtil.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class CommodityServiceImpl  extends CrudService<CommodityMapper,Commodity> implements CommodityService {
    //    日志
    final static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Autowired
    PictureMapper pictureMapper;
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    CommodityTypeMapper commodityTypeMapper;


    @Override
    public ResultModel selectAllCommodity(List<Map<String,Object>> permissionList, String permissionName) {
        //初始化结果集
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限->start");
        for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                logger.info("查询全部商品分类->start");
                List<Map<String,Object>> commodityList = commodityMapper.selectAllCommodity();
                Map<String,Object> commodityMap = new  HashMap<String,Object>();
                commodityMap.put("commodityList",commodityList);
                List<Map<String,Object>> commodityAllList = commodityTypeMapper.selectAllCommodityTypeById(commodityMap);
                if(commodityAllList.size() != 0){
                    for (Map commodityAllMap: commodityAllList) {
                        if((int)commodityAllMap.get("commodityStatus") == 10){
                            commodityAllMap.put("statusCode",WizardAuditEnum.existenceStatusEnum.STATUS_SUCCESS.getDesc());
                        }
                        else {
                            commodityAllMap.put("statusCode",WizardAuditEnum.existenceStatusEnum.STATUS_FAIL.getDesc());
                        }
                    }
                    result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
                    result.setData(commodityList);
                }
                logger.info("查询全部商品分类->end");
            }
        }
        return result;
    }

    @Override
    public ResultModel deleteCommodity(List<Map<String,Object>> permissionList, String permissionName, Commodity commodity) {
        //初始化结果集
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("验证权限->start");
        for (Map map:permissionList) {
            if(map.get("permissionName").equals(permissionName)){
                logger.info("删除用户和商品->start");
                int commodityTypeInfluence = commodityMapper.deleteCommodity(commodity);
                if(commodityTypeInfluence > 0){
                    result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
                }
                logger.info("删除用户和商品->end");
            }
        }
        return result;
    }

    /**
     * Describe: 分页查询商品信息
     * @author 张新飞
     * @date 2018/12/19
     * @parms  * @param null
     * @return
     */
    public List<Map<String, Object>> selectCommodityList(String where) {
        return getDao().selectCommodityList(where);
    }

    @Override
    public int insertCommodity(Commodity commodity) {
        return commodityMapper.insertCommodity(commodity);
    }

    @Override
    public Commodity selectByPrimaryKey(Integer id) {
        return commodityMapper.selectByPrimaryKey(id);
    }

    /**
     * Describe: 上传接口
     * @author 张新飞
     * @date 2018/12/26
     * @parms  * @param null
     * @return
     */
    @Override
    public ResultModel uploadImages(HttpServletRequest request, @RequestParam(value = "file") MultipartFile[] files, String createData) {
        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(), WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
        logger.info("================上传照片开始================");
        //压缩照片，创建对象
        ImageCompress imageCompress = new ImageCompress();
        //定义序号    
        int count = 1;
        //定义集合，最多存3张照片，批量存入数据库
        List<Picture> pictureList = new ArrayList<>();
        //当前项目路径（存储图片的文件夹）
        String srcPath = request.getSession().getServletContext().getRealPath("/srcImage");
        String desPath = request.getSession().getServletContext().getRealPath("/desImage");
          File fileUrl = new File(desPath);
            //如果当前项目里不存在images文件夹，就创建
            if (!fileUrl.exists()) {
                fileUrl.mkdir();
            }
        System.out.println(files.length);
        //定义一个当前商品的统一id,把当前时间作为同一商品的统一ID
        String pictureID = createData;
        for (MultipartFile img : files) {
            //压缩照片
            String imagePath = imageCompress.commpressPicForScale(img,srcPath,desPath,300,0.8);
            //最多只能上传3张图片
            if (count <= 3) {
                if (!img.isEmpty()) {
                    //生成一个唯一标识符给图片命名，避免图片名重复，覆盖原有图片            
                    String name = UUID.randomUUID().toString().replaceAll("-", "");
                    // 文件的扩展名
                    //img.getOriginalFilename() 是上传图片的原始名字
                    String ext = FilenameUtils.getExtension(img.getOriginalFilename());
                    try {
                        //transferTo()方法将上传的文件写到服务器指定的文件
                        img.transferTo(new File(imagePath + "/" + name + "." + ext));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // 存到数据库的路径（相对路径）
                    String url = "desImage/" + name + "." + ext;
                    System.out.println("数据库路径：" + url);
                    Picture picture = new Picture();
                    picture.setID(pictureID);
                    picture.setImgUrl(url);
                    pictureList.add(picture);
                    count++;
                }
            }
        }
        Map<String, Object> pictureMap = new HashMap<>();
        pictureMap.put("pictureList", pictureList);
        //插入数据库
        int Influence = pictureMapper.insertPictureList(pictureMap);
        if (Influence > 0) {
            result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(), WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
            //把商品照片的统一ID返回
            result.setData(pictureID);
        }
        logger.info("================上传照片结束================");
        return result;
    }

    @Override
    public List<Map<String, Object>> findByPage(Map<String, Object> map) {
        return commodityMapper.findByPage(map);
    }


}






/**
 * Describe: 分页查询商品信息
 * @author 张新飞
 * @date 2018/12/19
 * @parms  * @param null
 * @return
 */
//    public ResultModel findCommodityByPage(Map<String, Object> params) {
//        ResultModel result = ResultUtil.info(WizardAuditEnum.StatusEnum.STATUS_FAIL.getValue(),WizardAuditEnum.StatusEnum.STATUS_FAIL.getDesc());
//        Page page = new Page();
//        page.setPageSize((Integer)params.get("pageSize"));
//        //获取总记录数
//        page.setTotalCount(commodityMapper.getCount(params));
//        //总页数
//        page.setTotalPage(page.getTotalPage(page.getTotalCount(),page.getPageSize()));
//        //当前页，索引
//        page.setPageNo((int)params.get("pageIndex"));
//        params.put("pageInfo",page);
//        //获取查询到的结果集
//        List<Map<String,Object>> list = commodityMapper.findByPage(params);
//        if(list.size()>0){
//            page.setList(list);
//            result = ResultUtil.success(WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getValue(),WizardAuditEnum.StatusEnum.STATUS_SUCCESS.getDesc());
//            result.setData(page);
//        }else{
//            page.setList(null);
//        }
//
//        return result;
//    }