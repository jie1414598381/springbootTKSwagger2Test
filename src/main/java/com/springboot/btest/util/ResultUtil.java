package com.springboot.btest.util;


import com.springboot.btest.common.Result;
import com.springboot.btest.common.exception.ExceptionEnum;

import static com.springboot.btest.common.Const.MSG_OK;

public class ResultUtil {
    public static Result ok(Object object) {
        return new Result(0, MSG_OK, object);
    }

    public static Result success() {
        return ok(null);
    }

    public static Result error(Integer code, String msg) {
        return new Result(code, msg);
    }

    public static Result error(ExceptionEnum ee) {
        return new Result(ee.getCode(), ee.getMsg());
    }
}
