//package com.soecode.lyf.util.ImageUntil;
//
//import com.sun.javaws.Globals;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.File;
//import java.util.UUID;
//
///** 照片上传
// * @ Author ：zhangxinfei
// * @ Date   ：Created in 14:06 2018/12/22
// */
//public class ImageUntil {
//
//}
//
//    public static String imageUpload(HttpServletRequest request, String directory, MultipartFile file) throws Exception {
//// 文件保存路径
//        String realPath = Globals.USER_IMAGES + directory;
//        //创建目录
//        FileUtils.createDirectory(Globals.getUserImagesBaseDir() + realPath);
//        //获取文件名
//        // UUID.randomUUID()加上这个是为了防止上传相同图片
//        String fileName = UUID.randomUUID() + file.getOriginalFilename();
//        // 转存文件
//        file.transferTo(new File(Globals.getUserfilesBaseDir() + realPath + "/" + fileName));
//
//        return realPath + fileName;
//    }
//
//
//
//}
