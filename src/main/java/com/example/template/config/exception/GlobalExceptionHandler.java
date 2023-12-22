package com.example.template.config.exception;

import com.example.template.common.data.HttpResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author created by sunjy on 12/21/23
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public HttpResult handleException(Exception e, HttpServletRequest request) {
        log.error("Exception Occurred", e);
        return HttpResult.error(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public HttpResult handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        log.error("Runtime Exception Occurred", e);
        return HttpResult.error(e.getMessage());
    }

}
