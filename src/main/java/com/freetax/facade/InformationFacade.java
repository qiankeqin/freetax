package com.freetax.facade;

import com.freetax.facade.user.UserFacade;
import com.freetax.mybatis.information.entity.Information;
import com.freetax.mybatis.information.entity.InformationVo;
import com.freetax.mybatis.information.service.InformationService;
import com.freetax.utils.pagination.model.Paging;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author zhurui
 * @Date 2017/11/13 11:04
 */
@Service
public class InformationFacade {

    @Autowired
    private InformationService informationService;

    @Autowired
    private UserFacade userFacade;

    /**
     * 新增资讯文章
     * @param title
     * @param subhead
     * @param content
     * @param coverimg
     * @param type
     * @param ishot
     * @param source
     */
    public void insertInformation(String title,String subhead,String content,String coverimg,String type,String ishot,String source){
        Information information = new Information();
        if (StringUtils.isNotEmpty(title)){
            information.setTitle(title);
        }
        if (StringUtils.isNotEmpty(subhead)){
            information.setSubhead(subhead);
        }
        if (StringUtils.isNotEmpty(content)){
            information.setContent(content);
        }
        if (StringUtils.isNotEmpty(coverimg)){
            information.setCoverimg(coverimg);
        }
        if (StringUtils.isNotEmpty(type)){
            information.setType(Integer.parseInt(type));
        }
        if (StringUtils.isNotEmpty(ishot)){
            information.setIshot(Integer.parseInt(ishot));
        }
        if (StringUtils.isNotEmpty(source)){
            information.setSource(source);
        }
        information.setIsdel(0);
        information.setIntime(new Date());
        informationService.insertInformation(information);
    }

    /**
     * 修改资讯文章
     * @param title
     * @param subhead
     * @param content
     * @param coverimg
     * @param type
     * @param ishot
     * @param source
     */
    public void updateInformation(String id,String title,String subhead,String content,String coverimg,String type,String ishot,String source){
        Information information = new Information();
        if (StringUtils.isNotEmpty(id)){
            information.setId(Integer.parseInt(id));
        }
        if (StringUtils.isNotEmpty(title)){
            information.setTitle(title);
        }
        if (StringUtils.isNotEmpty(subhead)){
            information.setSubhead(subhead);
        }
        if (StringUtils.isNotEmpty(content)){
            information.setContent(content);
        }
        if (StringUtils.isNotEmpty(coverimg)){
            information.setCoverimg(coverimg);
        }
        if (StringUtils.isNotEmpty(type)){
            information.setType(Integer.parseInt(type));
        }
        if (StringUtils.isNotEmpty(ishot)){
            information.setIshot(Integer.parseInt(ishot));
        }
        if (StringUtils.isNotEmpty(source)){
            information.setSource(source);
        }
        informationService.updateInformation(information);
    }

    /**
     * 删除资讯文章
     * @param id
     */
    public void deleteInformation(String id){
        informationService.deleteInformation(Integer.parseInt(id));
    }

    /**
     * boss查询资讯文章列表
     * @param title
     * @param type
     * @param ishot
     * @param source
     * @param pag
     * @return
     */
    public List<Information> queryInformationByList(String title, String type, String ishot,String source, String intime, Paging<Information> pag){
        InformationVo information = new InformationVo();
        if (StringUtils.isNotEmpty(title)){
            information.setTitle(title);
        }
        if (StringUtils.isNotEmpty(type)){
            information.setType(Integer.parseInt(type));
        }
        if (StringUtils.isNotEmpty(ishot)){
            information.setIshot(Integer.parseInt(ishot));
        }
        if (StringUtils.isNotEmpty(source)){
            information.setSource(source);
        }
        if (StringUtils.isNotEmpty(intime)){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Map<String,Date> map = userFacade.getDateTimeMap(intime,format);
            if (map != null){
                information.setIntimein(map.get("begin"));
                information.setIntimeend(map.get("end"));
            }
        }
        return informationService.findAllQueryInformationByList(information,pag);
    }

    /**
     * PC查询资讯文章详情，并返回总共多少条
     * @param id
     * @return
     */
    public InformationVo queryInformationToPc(String id){
        //查询资讯文章详情，并返回总共多少条
        InformationVo vo = informationService.queryInformationToPc(Integer.parseInt(id));

        return vo;
    }

    /**
     * PC查询资讯文章列表
     * @param type
     * @param ishot
     * @param pag
     * @return
     */
    public List<Information> queryInformationByListPc(String type,String ishot, Paging<Information> pag){
        Information information = new Information();
        if (StringUtils.isNotEmpty(type)){
            information.setType(Integer.parseInt(type));
        }
        if (StringUtils.isNotEmpty(ishot)){
            information.setIshot(Integer.parseInt(ishot));
        }
        return informationService.findAllQueryInformationByListPC(information,pag);
    }


    /**
     * 根据id查询资讯文章详情
     * @param id
     * @return
     */
    public Information queryInformationById(String id){
        return informationService.queryInformationById(Integer.parseInt(id));
    }

    /**
     * 资讯文章设为精选/取消精选
     * @param id
     * @param ishot
     */
    public void insertInformationByIsHot(String id,String ishot){
        Information information = new Information();
        if (StringUtils.isNotEmpty(id)){
            information.setId(Integer.parseInt(id));
        }
        if (StringUtils.isNotEmpty(ishot)){
            information.setIshot(Integer.parseInt(ishot));
        }
        information.setHottime(new Date());
        informationService.insertInformationByIsHot(information);
    }
}
