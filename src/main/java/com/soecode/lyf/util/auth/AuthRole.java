package com.soecode.lyf.util.auth;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * Created by WXL on 2017/7/6.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
//最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface AuthRole {
    /**
     *@param
     */

    String modelName() default "";

    String auth() default "VIEW"; //操作权限;
    String authsCode();//权限代码
    String typeCode();//操作类型 业务精灵 资源精灵 管理平台 运营平台
    String operating();//操作 上报 ，保存 ，删除
}
