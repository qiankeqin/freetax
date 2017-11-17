package com.freetax.utils;

import com.freetax.common.constant.MsgCodeConstant;
import com.freetax.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhurui
 * @Date 2017/11/14 14:00
 */
@Service
public class MovisionOssClient {


    private static Logger log = LoggerFactory.getLogger(MovisionOssClient.class);

    /**
     * 裁剪PNG图片工具类
     *
     * @param fromFile     源文件
     * @param toFile       裁剪后的文件
     * @param outputWidth  裁剪宽度
     * @param outputHeight 裁剪高度
     * @param proportion   是否是等比缩放
     */
    public Map resizePng(File fromFile, File toFile, int outputWidth, int outputHeight, int outputx, int outputy,
                         boolean proportion) {
        Map resault = new HashMap();
        try {
            BufferedImage bi2 = ImageIO.read(fromFile);
            int newWidth;
            int newHeight;
            // 判断是否是等比缩放
            if (proportion) {
                // 为等比缩放计算输出的图片宽度及高度
                double rate1 = ((double) bi2.getWidth(null)) / (double) outputWidth + 0.1;
                double rate2 = ((double) bi2.getHeight(null)) / (double) outputHeight + 0.1;
                // 根据缩放比率大的进行缩放控制
                double rate = rate1 < rate2 ? rate1 : rate2;
                newWidth = (int) (((double) bi2.getWidth(null)) / rate);
                newHeight = (int) (((double) bi2.getHeight(null)) / rate);
            } else {
                newWidth = outputWidth; // 输出的图片宽度
                newHeight = outputHeight; // 输出的图片高度
            }
            BufferedImage to = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = to.createGraphics();
            to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight,
                    Transparency.TRANSLUCENT);
            g2d.dispose();
            g2d = to.createGraphics();
            @SuppressWarnings("static-access")
            //Image from = bi2.getScaledInstance(newWidth, newHeight, bi2.SCALE_AREA_AVERAGING);
                    Image from = bi2.getSubimage(outputx, outputy, newWidth, newHeight);
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose();
            ImageIO.write(to, "png", toFile);
            resault.put("code", 200);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("错误信息", e);
            resault.put("code", 300);
        }
        return resault;
    }



    /**
     * 图片文件上传（上传本地图片用于帖子封面切割）
     *
     * @param file  file
     * @return url | filename
     */
    public Map<String, Object> uploadMultipartFileObject(MultipartFile file) {
        log.debug("上传到正式域名OSS");
        Map map = uploadMultipartFile(file, 1);
        String status = String.valueOf(map.get("status"));
        if (status.equals("success")) {
            return map;
        } else {
            log.error("上传失败");
            throw new BusinessException(MsgCodeConstant.SYSTEM_ERROR, "上传失败");
        }
    }


    /**
     * 图片上传到本地服务器
     *
     * @param file
     * @return
     */
    public Map uploadMultipartFile(MultipartFile file, Integer type) {
        //获取文件名
        String filename = file.getOriginalFilename();
        Map map = new HashMap();
        if (file.getSize() > 0) {
            try {
                String path = null;
                String url = null;
                if (type == 1) {
                    //查询帖子保存路径
                    String domainurl = PropertiesLoader.getValue("test.img.domain");
                    //String domainurl = "d:/1/";
                    path = domainurl + filename;
                    url = path;
                }
                map.put("status", "success");
                map.put("url", url);
                //返回图片的宽高
                BufferedImage bi = ImageIO.read(file.getInputStream());
                map.put("width", bi.getWidth());
                map.put("height", bi.getHeight());
                file.transferTo(new File(path));
                return map;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("原图上传报错");
                return null;
            }
        }
        return map;
    }

}
