package com.soecode.lyf.util.imagesUtil;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
/**
 * @ Author ：zhangxinfei
 * @ Date   ：Created in 21:24 2018/12/26
 */
public class ImageCompress {
    /**
     *  上传的图片                                                   文件夹               上传图片路径                    最大图片尺寸    精度    
     * ImageCompress.commpressPicForScale(MultipartFile srcPath,"F:/111","F:/111/7777.jpg", 500, 0.8); // 图片小于500kb
     *  
     *      * 根据指定大小和指定精度压缩图片
     *      * 
     *      * @param srcPath
     *      *            源图片地址
     *      * @param desPath
     *      *            目标图片地址
     *      * @param desFilesize
     *      *            指定图片大小，单位kb
     *      * @param accuracy
     *      *            精度，递归压缩的比率，建议小于0.9
     *      * @return
     *      
     */
    public String commpressPicForScale(MultipartFile srcPath, String load, String desPath, long desFileSize, double accuracy) {
//        if (StringUtils.isEmpty(srcPath) || StringUtils.isEmpty(srcPath)) {
//            return null;
//        }
        if ("".equals(srcPath) || srcPath == null || "".equals(desPath) || desPath == null) {
            return null;
        }
//        if (!new File(srcPath).exists()) {
//            return null;
//        }
        try {
//        	File srcFile = srcPath.transferTo();
//            File srcFile = new File(srcPath);
            CommonsMultipartFile cf = (CommonsMultipartFile) srcPath;
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();
            File f = fi.getStoreLocation();
            long srcFileSize = f.length();
            System.out.println("源图片：" + srcPath + "，大小：" + srcFileSize / 1024 + "kb");
            File file = new File(load);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdir();
            }
            // 1、先转换成jpg
            Thumbnails.of(f).scale(1f).toFile(desPath);
            // 递归压缩，直到目标文件大小小于desFileSize
            commpressPicCycle(desPath, desFileSize, accuracy);

            File desFile = new File(desPath);
            System.out.println("目标图片：" + desPath + "，大小" + desFile.length() / 1024 + "kb");
            System.out.println("图片压缩完成！");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return desPath;
    }

    private static void commpressPicCycle(String desPath, long desFileSize,
                                          double accuracy) throws IOException {
        File srcFileJPG = new File(desPath);
        long srcFileSizeJPG = srcFileJPG.length();
        // 2、判断大小，如果小于500kb，不压缩；如果大于等于500kb，压缩
        if (srcFileSizeJPG <= desFileSize * 1024) {
            return;
        }
        // 计算宽高
        BufferedImage bim = ImageIO.read(srcFileJPG);
        int srcWdith = bim.getWidth();
        int srcHeigth = bim.getHeight();
        int desWidth = new BigDecimal(srcWdith).multiply(
                new BigDecimal(accuracy)).intValue();
        int desHeight = new BigDecimal(srcHeigth).multiply(
                new BigDecimal(accuracy)).intValue();

        Thumbnails.of(desPath).size(desWidth, desHeight).outputQuality(accuracy).toFile(desPath);
        commpressPicCycle(desPath, desFileSize, accuracy);
    }
}