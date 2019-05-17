package com.springboot.btest.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.springboot.btest.common.exception.AppException;
import com.springboot.btest.config.GlobalConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.*;

import static com.springboot.btest.common.Const.ACCESS_TOKEN_URL;
import static com.springboot.btest.common.Const.GETWXACODEUNLIMIT_URL;
import static com.springboot.btest.common.exception.ExceptionEnum.ERR_WX_API;
import static com.springboot.btest.common.exception.ExceptionEnum.GEN_QRCODE_ERR;

@Service
public class WxHttpService {
    private final static Logger LOGGER = LoggerFactory.getLogger(WxHttpService.class);

    @Autowired
    private RestTemplate restTemplate;
//    @Autowired
//    private RedisService redisService;
    @Autowired
    private GlobalConfig globalConfig;

    /**
     * 获取微信access_token
     * 先从redis取，取不到再调用微信api
     *
     * @return
     */
    public String getAccessToken() {
//        String accessToken = redisService.get(ACCESS_TOKEN_KEY);
//        if (!Strings.isNullOrEmpty(accessToken)) {
//            return accessToken;
//        }

        JSONObject result = restTemplate.getForEntity(String.format(ACCESS_TOKEN_URL, globalConfig.getAppId(), globalConfig.getAppSecret()), JSONObject.class).getBody();
        if (null == result) {
            LOGGER.error("getAccessToken err", result);
            return null;
        }
        String accessToken = result.getString("access_token");
      //  Long expiresIn = Long.valueOf((Integer) result.get("expires_in"));
        if (null == accessToken) {
            LOGGER.error("getAccessToken err", result);
            return null;
        }
//
//        if (null == expiresIn || expiresIn <= 0) {
//            expiresIn = 7200L;
//        }
//        redisService.set(ACCESS_TOKEN_KEY, accessToken, expiresIn);

        return accessToken;
    }

    /**
     * 获取带参数小程序二维码
     *
     * @param scene
     * @return
     */
    public void getWXAcodeUnlimit(String scene, File qrCodeFile) {
        String accessToken = getAccessToken();
        LOGGER.info("======wx accessToken: {}", accessToken);
        if (Strings.isNullOrEmpty(accessToken)) {
            throw new AppException(ERR_WX_API);
        }
        final String url = GETWXACODEUNLIMIT_URL + accessToken;

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(qrCodeFile);

            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.APPLICATION_JSON_UTF8;
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            headers.add("Accept", type.toString());

            JSONObject params = new JSONObject();
            params.put("scene", scene);
            params.put("page", globalConfig.getWx_mood_page());
            params.put("is_hyaline", true);
            params.put("width", 280);//最小

            HttpEntity<String> entity = new HttpEntity<>(params.toJSONString(), headers);

            ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.POST, entity, byte[].class);
            FileCopyUtils.copy(response.getBody(), outputStream);
//            LOGGER.info("获取小程序二维码: {}", new String(response.getBody(), "UTF-8")); // 打印出来会很多

            outputStream.flush();
        } catch (Exception e) {
            LOGGER.error("gen qrcode err: ", e);
            throw new AppException(GEN_QRCODE_ERR);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
