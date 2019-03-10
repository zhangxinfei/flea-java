//package com.soecode.lyf.util.redis.cache;
//
//import java.io.Serializable;
//import java.lang.reflect.Method;
//
//import com.soecode.lyf.controller.UserController;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//@Aspect
//public class GetCacheAOP  {
//
//    @Autowired
//    private RedisTemplate<Serializable, Object> redisTemplate;
//
//    private RedisCache redisCache = new RedisCache();
//
////    private RedisUtilServiceImpl redisUtilServiceImpl = new RedisUtilServiceImpl();
//
//    //日志
//    final static Logger logger = LoggerFactory.getLogger(UserController.class);
//
//    @Pointcut("@annotation(com.soecode.lyf.util.redis.cache.GetCache)")
//    public void getCache(){
////        System.out.println("我是一个切入点");
//    }
//
//    /**
//     * 在所有标注@getCache的地方切入
//     * @param joinPoint
//     */
//    @Around("getCache()")
//    public Object beforeExec(ProceedingJoinPoint joinPoint){
//        //前置：到redis中查询缓存
//        logger.info("前置：查询Redis中的缓存->start");
//        //redis中key格式：    id
//        String redisKey = getCacheKey(joinPoint);
//        //通过键获取Redis中所有的值
//        //redisUtilServiceImpl.sGet(redisKey);
//        //获取从redis中查询到的对象
//        Object objectFromRedis = redisCache.getDataFromRedis(redisKey);
//        //如果查询到了
//        if(null != objectFromRedis){
//            logger.info("前置：查询Redis中的缓存->end");
//            return objectFromRedis;
//        }
//        logger.info("查询数据库->start");
//        //没有查到，那么查询数据库
//        Object object = null;
//        try {
//            object = joinPoint.proceed();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        logger.info("查询数据库->end");
//        //后置：将数据库中查询的数据放到redis中
//        redisCache.setDataToRedis(redisKey, object);
////        System.out.println("redis中的数据..."+object.toString());
//        //将查询到的数据返回
//        return object;
//    }
//
//    /**
//     * 根据类名、方法名和参数值获取唯一的缓存键
//     * @return 格式为 "包名.类名.方法名.参数类型.参数值"，类似 "your.package.SomeService.getById(int).123"
//     */
//
//    @SuppressWarnings("unused")
//    private String getCacheKey(ProceedingJoinPoint joinPoint) {
//
//
//        MethodSignature ms=(MethodSignature) joinPoint.getSignature();
//        Method method=ms.getMethod();
//        String ActionName = method.getAnnotation(GetCache.class).name();
//        String fieldList = method.getAnnotation(GetCache.class).value();
//        //System.out.println("签名是"+ms.toString());
//        for (String field:fieldList.split(","))
//            ActionName +="."+field;
//
//        //先获取目标方法参数
//        String id = null;
//        Object[] args = joinPoint.getArgs();
//        if (args != null && args.length > 0) {
//            id = String.valueOf(args[0]);
//        }
//
//        ActionName += "="+id;
//        String redisKey = ms+"."+ActionName;
//        return redisKey;
//    }
//
//
//    public void setRedisTemplate(
//            RedisTemplate<Serializable, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }
//}
