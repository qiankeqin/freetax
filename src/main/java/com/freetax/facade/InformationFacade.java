package com.freetax.facade;

import com.freetax.mybatis.information.entity.Information;
import com.freetax.mybatis.information.service.InformationService;
import com.freetax.utils.pagination.model.Paging;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author zhurui
 * @Date 2017/11/13 11:04
 */
@Service
public class InformationFacade {

    @Autowired
    private InformationService informationService;

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

    public List<Information> queryInformationByList(String title, String type, String ishot,String source, Paging<Information> pag){
        Information information = new Information();
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
        return informationService.findAllQueryInformationByList(information,pag);
    }
}
