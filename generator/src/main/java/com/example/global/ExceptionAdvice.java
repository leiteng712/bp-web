package com.example.global;


import com.example.global.exception.SPIException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author: leiteng
 * @date: 2019/12/18 13:13
 */
@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(SPIException.class)
    @ResponseBody
    public Map handlerSPIException(SPIException e) {
        log.error("", e);
        return ResponseResult.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map handlerException(Exception e) {
        log.error("", e);
        return ResponseResult.error(e.getMessage());
    }
}
