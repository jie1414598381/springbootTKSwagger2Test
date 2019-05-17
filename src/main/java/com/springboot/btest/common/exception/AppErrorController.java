package com.springboot.btest.common.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.springboot.btest.common.exception.ExceptionEnum.RES_NOT_FOUND;


@Controller
public class AppErrorController implements ErrorController {
    private final static String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    public void error() {
        throw new AppException(RES_NOT_FOUND);
    }
}
