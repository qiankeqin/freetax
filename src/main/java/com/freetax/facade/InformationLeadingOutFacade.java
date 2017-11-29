package com.freetax.facade;

import com.freetax.mybatis.advisory.entity.Advisory;
import com.freetax.mybatis.advisory.service.AdvisoryService;
import com.freetax.mybatis.information.entity.Information;
import com.freetax.mybatis.information.service.InformationService;
import com.freetax.utils.PropertiesLoader;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author zhurui
 * @Date 2017/11/29 11:54
 */
@Service
public class InformationLeadingOutFacade {

    @Autowired
    private InformationService informationService;

    private static Logger log = LoggerFactory.getLogger(InformationLeadingOutFacade.class);

    public Map excelLeadingOutByInformation() {
        Map resault = new HashMap();
        try {
            //t.xls为要新建的文件名
            String path = "d:/1/";
            //查询帖子保存路径
            //String path = PropertiesLoader.getValue("excel.domain");
            UUID uuid = UUID.randomUUID();
            //查询出所有xml导入的帖子
            List<Information> informations = informationService.queryUserByAll();
            String urlname = "/" + getDateString() + uuid + ".xls";
            log.info("...................." + path + urlname);
            WritableWorkbook book = Workbook.createWorkbook(new File(path + urlname));
            //生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("第一页", 0);

            /*PostXml postXml = new PostXml();
            Field[] fields = postXml.getClass().getDeclaredFields();*/
            String title[] = {"id", "标题", "副标题", "封面", "类型", "咨询来源", "时间", "内容"};
            //设计表头
            for (int i = 0; i < title.length; i++) {
                sheet.addCell(new Label(i, 0, title[i]));
            }
           /* Post post = new Post();
            //获取对象长度
            Field[] p = post.getClass().getDeclaredFields();*/
            //遍历循环出集合中每一个元素，写到表中
            addExcelFileElement(sheet, informations);
            //
            //写入数据
            book.write();
            if (book != null) {
                //关闭流
                book.close();
            }
            //用于返回文件路径
            //String reurl = PropertiesLoader.getValue("excel.file.url");
            String reurl = "d:/1/";
            reurl += "excel" + urlname;
            resault.put("code", 200);
            resault.put("date", reurl);
            resault.put("massger", "成功");
            return resault;
        } catch (Exception e) {
            e.printStackTrace();
            resault.put("code", 400);
            resault.put("date", -1);
            resault.put("massger", "失败");
            return resault;
        }
    }

    public String getDateString() {
        Long l = new Date().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(l);
        String name = simpleDateFormat.format(date);
        return name;
    }

    /**
     * 为excel文件添加元素
     *
     * @param sheet
     * @param informations
     * @throws IllegalAccessException
     * @throws WriteException
     */
    private void addExcelFileElement(WritableSheet sheet, List<Information> informations) throws Exception {
        for (int i = 0; i < informations.size(); i++) {
            Information information = informations.get(i);
            /*Integer id = advisory.getId();
            Integer visit = advisory.getVisit();
            String name = advisory.getName();
            String phone = advisory.getPhone();
            Date intime = advisory.getIntime();*/
            //写到表格中
            setExcelCell(sheet, i, information);
        }
    }

    /**
     * 表格中填入数据
     *
     * @param sheet
     * @throws WriteException
     */
    private void setExcelCell(WritableSheet sheet, int i, Information information) throws Exception {
        //获取对象长度
        Field[] p = information.getClass().getDeclaredFields();
        int k = 0;
        while (k < p.length) {
            if (k == 0) {
                sheet.addCell(new Label(k, i + 1, information.getId().toString()));

            }
            if (k == 1) {
                sheet.addCell(new Label(k, i + 1, information.getTitle()));

            }
            if (k == 2) {
                sheet.addCell(new Label(k, i + 1, information.getSubhead()));

            }
            if (k == 3) {
                sheet.addCell(new Label(k, i + 1, information.getCoverimg()));

            }
            if (k == 4) {
                if (information.getType() == 1) {
                    sheet.addCell(new Label(k, i + 1, "行业资讯"));
                } else if (information.getType() == 2) {
                    sheet.addCell(new Label(k, i + 1, "财税知识"));
                } else if (information.getType() == 3) {
                    sheet.addCell(new Label(k, i + 1, "政策案例"));
                }

            }
            if (k == 5) {
                sheet.addCell(new Label(k, i + 1, information.getSource()));

            }
            if (k == 6) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sheet.addCell(new Label(k, i + 1, sdf2.format(sdf1.parse(information.getIntime().toString())) + ""));

            }
            if (k == 7) {
                sheet.addCell(new Label(k, i + 1, information.getContent()));

            }
            k++;
        }
    }


}
