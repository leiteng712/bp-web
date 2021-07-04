package com.example.annotation;

import java.lang.annotation.*;

/**
 * @author: leite
 * @date: 2021/1/30 15:12
 * 返回的结果封装上
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OuterResponseBody {
    boolean enable() default true;
}
