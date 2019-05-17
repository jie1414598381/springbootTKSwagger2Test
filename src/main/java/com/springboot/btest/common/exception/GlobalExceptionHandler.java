package com.springboot.btest.common.exception;

import com.springboot.btest.common.Result;
import com.springboot.btest.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AopInvocationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;

import static com.springboot.btest.common.exception.ExceptionEnum.*;


/**
 * 统一异常捕获类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result errHandler(HttpServletRequest req, Exception e) {
        logger.error("+++++++++++++>>>>>: " + req.getRequestURL().toString(), e);
        if (e instanceof AppException) {
            AppException ae = (AppException) e;
            return ResultUtil.error(ae.getCode(), ae.getMessage());
        } else if (e instanceof HttpMessageNotReadableException) {
            return ResultUtil.error(WRONG_REQ);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return ResultUtil.error(ERR_REQ_TYPE);
        } else if (e instanceof MultipartException) {
            return ResultUtil.error(NOT_A_MULTIPART_REQUEST);
        }else if (e instanceof AopInvocationException){
            logger.error("操作数据库数据不存在返回NULL，aop异常");
        }
        return ResultUtil.error(INTERNAL_ERROR);
    }
}
