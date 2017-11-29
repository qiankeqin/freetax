package com.freetax.facade;

import com.freetax.mybatis.advisory.entity.Advisory;
import com.freetax.mybatis.advisory.service.AdvisoryService;
import com.freetax.mybatis.user.entity.User;
import com.freetax.mybatis.user.entity.UserExcel;
import com.freetax.mybatis.user.service.UserService;
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
public class ConsultlLeadingOutFacade {

    @Autowired
    private AdvisoryService advisoryService;

    private static Logger log = LoggerFactory.getLogger(ConsultlLeadingOutFacade.class);

    public Map excelLeadingOutByConsultl() {
        Map resault = new HashMap();
        try {
            //t.xls为要新建的文件名
            //String path = "d:/1";
            //查询帖子保存路径
            String path = PropertiesLoader.getValue("excel.domain");
            UUID uuid = UUID.randomUUID();
            //查询出所有xml导入的帖子
            List<Advisory> advisories = advisoryService.queryUserByAll();
            String urlname = "/" + getDateString() + uuid + ".xls";
            log.info("...................." + path + urlname);
            WritableWorkbook book = Workbook.createWorkbook(new File(path + urlname));
            //生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("第一页", 0);

            /*PostXml postXml = new PostXml();
            Field[] fields = postXml.getClass().getDeclaredFields();*/
            String title[] = {"id", "姓名", "手机", "是否回访", "咨询时间"};
            //设计表头
            for (int i = 0; i < title.length; i++) {
                sheet.addCell(new Label(i, 0, title[i]));
            }
           /* Post post = new Post();
            //获取对象长度
            Field[] p = post.getClass().getDeclaredFields();*/
            //遍历循环出集合中每一个元素，写到表中
            addExcelFileElement(sheet, advisories);
            //
            //写入数据
            book.write();
            if (book != null) {
                //关闭流
                book.close();
            }
            //用于返回文件路径
            String reurl = PropertiesLoader.getValue("excel.file.url");
            //String reurl = "d:/1/";
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
     * @param advisories
     * @throws IllegalAccessException
     * @throws WriteException
     */
    private void addExcelFileElement(WritableSheet sheet, List<Advisory> advisories) throws Exception {
        for (int i = 0; i < advisories.size(); i++) {
            Advisory advisory = advisories.get(i);
            /*Integer id = advisory.getId();
            Integer visit = advisory.getVisit();
            String name = advisory.getName();
            String phone = advisory.getPhone();
            Date intime = advisory.getIntime();*/
            //写到表格中
            setExcelCell(sheet, i, advisory);
        }
    }

    /**
     * 表格中填入数据
     *
     * @param sheet
     * @throws WriteException
     */
    private void setExcelCell(WritableSheet sheet, int i, Advisory advisories) throws Exception {
        //获取对象长度
        Field[] p = advisories.getClass().getDeclaredFields();
        int k = 0;
        while (k < p.length) {
            if (k == 0) {
                sheet.addCell(new Label(k, i + 1, advisories.getId().toString()));
                k++;
            }
            if (k == 1) {
                sheet.addCell(new Label(k, i + 1, advisories.getName()));
                k++;
            }
            if (k == 2) {
                sheet.addCell(new Label(k, i + 1, advisories.getPhone()));
                k++;
            }
            if (k == 3) {
                if (advisories.getVisit() == 1) {
                    sheet.addCell(new Label(k, i + 1, "已回访"));
                } else {
                    sheet.addCell(new Label(k, i + 1, "未回访"));
                }
                k++;
            }
            if (k == 4) {
                SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

                SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sheet.addCell(new Label(k, i + 1, sdf2.format(sdf1.parse(advisories.getIntime().toString())) + ""));
                k++;
            }
        }
    }
}
