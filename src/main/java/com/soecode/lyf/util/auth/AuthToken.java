package com.soecode.lyf.util.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * RUNTIME 保留至运行时。所以我们可以通过反射去获取注解信息。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthToken {
    String type() default "" ;


}


