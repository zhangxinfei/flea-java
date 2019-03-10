//package com.soecode.lyf.util.ImageUntil;
//
//import com.soecode.lyf.util.StringUtils;
//import com.sun.javaws.Globals;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Map;
//
///**
// *
// *  @ Author ：zhangxinfei
// *  @ Date   ：Created in 14:09 2018/12/22
// */
//public class Glbals {
//    /**
//     * 当前对象实例
//     */
//    private static Globals globals = new Globals();
//
//    //1.我用的是idea，在Application Context中配置了/cjhtest,所以request.getServerPort()+request.getContextPath()之间没加+“/”+
//    public static String getLocalPath(HttpServletRequest request) {
//        String imageLocal = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
//        return imageLocal;
//    }
//
//
//    //这个用于服务器，要把项目名跟在后面所以加了一个/
//    public static String getServerPath(HttpServletRequest request){
//        String imageServer = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath();
//        return imageServer;
//    }
//
//    /**
//     * 获取当前对象实例
//     */
//    public static Globals getInstance() {
//        return globals;
//    }
//
//    /**
//     * 保存全局属性值
//     */
//    private static Map<String, String> map = Maps.newHashMap();
//    /**
//     * 属性文件加载对象，引入你勇于配置的属性文件
//     */
//    private static PropertiesLoader loader = new PropertiesLoader("imagePath.properties");
//    /**
//     * 上传文件基础虚拟路径,定义的常亮，可以改成自己的
//     */
//    public static final String USER_IMAGES = "/chenjiahui1/";
//    /**
//     * 获取配置
//     *
//     */
//    public static String getConfig(String key) {
//        String value = map.get(key);
//        if (value == null){
//            value = loader.getProperty(key);
//            map.put(key, value != null ? value : StringUtils.EMPTY);
//        }
//        return value;
//    }
//    /**
//     * 获取上传文件的根目录
//     * @return
//     */
//    public static String getUserImagesBaseDir() {
//        String dir = getConfig("userImagesBaseDir.basedir");
//        if (StringUtils.isBlank(dir)){
//            try {
//                dir = ServletContextFactory.getServletContext().getRealPath("/");
//            } catch (Exception e) {
//                return "";
//            }
//        }
//        if(!dir.endsWith("/")) {
//            dir += "/";
//        }
//        // System.out.println(“userImagesBaseDir.basedir: ” + dir);
//        return dir;
//    }
//}
