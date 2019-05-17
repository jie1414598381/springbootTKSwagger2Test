package com.springboot.btest.common;

public class Const {
    public static final String MSG_OK = "OK";
    public static final String TOKEN = "token";
    public static final Integer DEFAULT_PAGENUM = 1;
    public static final Integer DEFAULT_PAGESIZE = 20;

    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    public static final String GETWXACODEUNLIMIT_URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";

    public static final long EXPIRED = 3 * 24 * 60 * 60;//3天
    public static final String CURRENT_SYSUSER = "current_sysuser";
    public static final String CURRENT_TOKEN = "current_token";
    public class RedisKeys {
        public static final String YULU_SYS_USER = "mood:yulu_sys_user";
    }

}
