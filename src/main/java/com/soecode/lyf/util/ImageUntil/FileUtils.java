//package com.soecode.lyf.util.ImageUntil;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//
///**
// *
// *  @ Author ：zhangxinfei
// *  @ Date   ：Created in 14:11 2018/12/22
// */
//public class FileUtils {
//    private static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);
//
//    /**
//     * 创建目录
//     * @param descDirName 目录名,包含路径
//     * @return 如果创建成功，则返回true，否则返回false
//     */
//    public static boolean createDirectory(String descDirName) {
//        String descDirNames = descDirName;
//        if (!descDirNames.endsWith(File.separator)) {
//            descDirNames = descDirNames + File.separator;
//        }
//        File descDir = new File(descDirNames);
//        if (descDir.exists()) {
//            logger.debug("目录 " + descDirNames + " 已存在!");
//            return false;
//        }
//// 创建目录
//        if (descDir.mkdirs()) {
//            logger.debug("目录 " + descDirNames + " 创建成功!");
//            return true;
//        } else {
//            logger.debug("目录 " + descDirNames + " 创建失败!");
//            return false;
//        }
//    }
//}
