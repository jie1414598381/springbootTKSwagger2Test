package com.springboot.btest.util;

import com.springboot.btest.common.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import static com.springboot.btest.common.exception.ExceptionEnum.DOWNLOAD_FAILED;

/**
 * 文件工具类
 */
public class PicUtil {
    private final static Logger logger = LoggerFactory.getLogger(PicUtil.class);

    /**
     * 下载文件到指定目录
     *
     * @param path
     * @param outputFile
     */
    public static void downloadFile(String path, String outputFile) {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            int byteread;
            URL url = new URL(path);
            URLConnection conn = url.openConnection();
            is = conn.getInputStream();
            fos = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1204];
            while ((byteread = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteread);
            }
            fos.flush();
        } catch (Exception e) {
            logger.error("downloadFile err: ", e);
            throw new AppException(DOWNLOAD_FAILED);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 合并文件
     *
     * @param originImg 原始图片
     * @param qrCode    二维码图片
     * @param x         二维码x坐标
     * @param y         二维码y坐标
     * @param targetImg 合成的图片
     */
    public static final void combineImage(String originImg, String qrCode, int x, int y, String targetImg) {
        try {
            BufferedImage big = ImageIO.read(new File(originImg));
            BufferedImage small = ImageIO.read(new File(qrCode));
            x = (big.getWidth() - small.getWidth())/2;
//            int width = big.getWidth();
//            int height = big.getHeight();
//            int smallWidth = Double.valueOf((30.0 / 315) * width).intValue();

            /** 靠左 */
            /*
            if (x <= 0) {
                x = Double.valueOf(width * (20.0 / 315)).intValue();
            }
            if (y <= 0) {
                y = Double.valueOf(height * (270.0 / 315)).intValue();
            }
            */
//            /** 靠右 */
//            if (x <= 0) {
//                x = width - Double.valueOf(width * (20.0 / 315)).intValue() - smallWidth;
//            }
//            if (y <= 0) {
//                y = Double.valueOf(height * (270.0 / 315)).intValue();
//            }

            Graphics2D g = big.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);
            g.dispose();

            ImageIO.write(big, targetImg.substring(targetImg.lastIndexOf(".") + 1), new File(targetImg));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
