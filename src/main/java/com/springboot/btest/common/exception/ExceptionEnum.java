package com.springboot.btest.common.exception;

public enum ExceptionEnum {
    INTERNAL_ERROR(10001, "服务异常"),
    RES_NOT_FOUND(10002, "资源不存在"),
    UNAUTHORIZED(10003, "鉴权失败"),
    PARAM_INVALID(10004, "参数非法"),
    WRONG_REQ(10005, "请求参数错误"),
    ERR_WX_API(10006, "调用微信api失败"),
    DOWNLOAD_FAILED(10007, "文件下载失败"),

    ERR_REQ_TYPE(10012, "请求类型错误"),
    FILE_UPLOAD_FAILED(20001, "文件上传失败"),
    NOT_A_MULTIPART_REQUEST(20002, "Current request is not a multipart request"),
    ERR_USERNAME_OR_PWD(20003, "用户名或者密码错误"),
    USER_NOT_EXIST(20004, "不存在该用户"),
    GEN_QRCODE_ERR(20005, "生成二维码图片失败"),;

    private Integer code;
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
