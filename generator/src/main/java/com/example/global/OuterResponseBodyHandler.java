package com.example.global;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description:
 * @author: huYong
 * @Date: 2020/6/12
 **/
@RestControllerAdvice
public class OuterResponseBodyHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        OuterResponseBody methodAnnotation = methodParameter.getMethodAnnotation(OuterResponseBody.class);
        if (methodAnnotation != null) {
            return methodAnnotation.enable();
        } else {
            OuterResponseBody annotation = methodParameter.getDeclaringClass().getAnnotation(OuterResponseBody.class);
            if (annotation != null) {
                return annotation.enable();
            }
            return false;
        }
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof IPage) {
            return ResponseResult.page((IPage) o);
        }
        return ResponseResult.obj(o);
    }
}
