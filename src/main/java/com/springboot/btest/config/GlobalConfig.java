package com.springboot.btest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "global")
public class GlobalConfig {

    private String wx_mood_page;
    private String file_upload_path;

    private String appId;
    private String appSecret;
    private String access_token_url;
    private String templateId;

    private boolean swaggerShow;
    private String sysSeqId;
    private String uploadPath;
    private String keyword1;
    //合成图片 固定图
    private String fixedImageUrl;

    public String getFixedImageUrl() {
        return fixedImageUrl;
    }

    public void setFixedImageUrl(String fixedImageUrl) {
        this.fixedImageUrl = fixedImageUrl;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getSysSeqId() {
        return sysSeqId;
    }

    public void setSysSeqId(String sysSeqId) {
        this.sysSeqId = sysSeqId;
    }

    public String getWx_mood_page() {
        return wx_mood_page;
    }

    public void setWx_mood_page(String wx_mood_page) {
        this.wx_mood_page = wx_mood_page;
    }

    public String getFile_upload_path() {
        return file_upload_path;
    }

    public void setFile_upload_path(String file_upload_path) {
        this.file_upload_path = file_upload_path;
    }

    public boolean isSwaggerShow() {
        return swaggerShow;
    }

    public void setSwaggerShow(boolean swaggerShow) {
        this.swaggerShow = swaggerShow;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAccess_token_url() {
        return access_token_url;
    }

    public void setAccess_token_url(String access_token_url) {
        this.access_token_url = String.format(access_token_url,getAppId(),getAppSecret());
    }

}
