package com.springboot.btest.util;

import com.springboot.btest.common.exception.AppException;
import org.springframework.validation.BindingResult;

import static com.springboot.btest.common.exception.ExceptionEnum.PARAM_INVALID;

public class ParamUtil {
    public static void valid(BindingResult result) throws AppException {
        if (result.hasErrors()) {
            throw new AppException(PARAM_INVALID.getMsg() + ":" + result.getFieldError().getDefaultMessage(), PARAM_INVALID.getCode());
        }
    }
}
