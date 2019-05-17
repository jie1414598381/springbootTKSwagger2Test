package com.springboot.btest.common.aspect;

import com.springboot.btest.common.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 返回结果处理切面
 **/
@Aspect
@Component
public class ResultAspect {
    @Resource
    private MappingJackson2HttpMessageConverter converter;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Pointcut("execution(public * com.springboot.btest.controller.*.*(..))")
    public void webLog(){}
    @Around(value = "webLog()")
    public void formatResult2JSON(ProceedingJoinPoint pjp) throws Throwable {
        Object object = null;
        object = pjp.proceed();
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        if (object instanceof Result) {
            converter.write(object, MediaType.APPLICATION_JSON_UTF8, outputMessage);
        } else {
            converter.write(new Result(object), MediaType.APPLICATION_JSON_UTF8, outputMessage);
        }
        shutdownResponse(response);
    }
    private void shutdownResponse(HttpServletResponse response) throws IOException {
        response.getOutputStream().close();
    }
}
