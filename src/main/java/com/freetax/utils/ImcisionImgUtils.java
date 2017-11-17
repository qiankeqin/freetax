package com.freetax.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author zhurui
 * @Date 2017/11/14 13:45
 */
@Service
public class ImcisionImgUtils {

    @Autowired
    private MovisionOssClient movisionOssClient;

    /**
     * 图片切割工具
     *
     * @param covimg 原图
     * @param whs    Map集合，其中 w,h为宽高,xy为开始坐标
     * @return
     */
    public Map imgCuttingUpload(String covimg, Map whs) {
        Map resaults = new HashMap();
        //查询帖子图片存放目录
        UUID uuid = UUID.randomUUID();
        String incise = PropertiesLoader.getValue("test.img.domain");
        //String incise = "d:/1/";
        //String incise = uploadFacade.getConfigVar("post.incise.domain");
        String suffix = covimg.substring(covimg.lastIndexOf(".") + 1);
        String filename = uuid + "." + suffix;
        incise += filename;
        //返回的图片路径为
        String resaulturl = incise;
        File fromFile = new File(covimg);
        File toFile = new File(incise);
        resaults.put("form", covimg);
        resaults.put("to", incise);
        resaults.put("new",resaulturl);
        //对宽高取整
        String w = whs.get("w").toString();
        if (w.indexOf(".") != -1) {
            w = w.substring(0, w.lastIndexOf("."));
        }
        String h = whs.get("h").toString();
        if (h.indexOf(".") != -1) {
            h = h.substring(0, h.lastIndexOf("."));
        }
        String x = whs.get("x").toString();
        if (x.indexOf(".") != -1) {
            x = x.substring(0, x.lastIndexOf("."));
        }
        String y = whs.get("y").toString();
        if (y.indexOf(".") != -1) {
            y = y.substring(0, y.lastIndexOf("."));
        }
        //2本地取文件并剪切,
        Map map = movisionOssClient.resizePng(fromFile, toFile, Integer.parseInt(w), Integer.parseInt(h), Integer.parseInt(x), Integer.parseInt(y), false);
        return resaults;
    }
}
