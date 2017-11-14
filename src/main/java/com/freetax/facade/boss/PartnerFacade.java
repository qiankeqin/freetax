package com.freetax.facade.boss;

import com.freetax.mybatis.partner.entity.Partner;
import com.freetax.mybatis.partner.service.PartnerService;
import com.freetax.utils.pagination.model.Paging;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author zhurui
 * @Date 2017/11/14 16:38
 */
@Service
public class PartnerFacade {

    @Autowired
    private PartnerService partnerService;

    /**
     * PC首页查询合作伙伴列表
     * @return
     */
    public List<Partner> queryPartnerList(){
        return partnerService.queryPartnerList();
    }

    public void insertPartner(String banner,String link){
        Partner partner = new Partner();
        if (StringUtils.isNotEmpty(banner)){
            partner.setBanner(banner);
        }
        if (StringUtils.isNotEmpty(link)){
            partner.setLink(link);
        }
        partner.setIntime(new Date());
        partner.setIsdel(0);
        partnerService.insertPartner(partner);
    }

    /**
     * 根据id查询合作伙伴详情
     * @param id
     * @return
     */
    public Partner queryPartnerById(String id){
        return partnerService.queryPartnerById(Integer.parseInt(id));
    }

    /**
     * 条件查询合作伙伴列表
     * @param name
     * @param pag
     * @return
     */
    public List<Partner> queryPartnerByList(String name, Paging<Partner> pag){
        Partner partner = new Partner();
        if (StringUtils.isNotEmpty(name)){
            partner.setName(name);
        }
        return partnerService.queryPartnerByList(partner,pag);
    }

    /**
     * 修改合作伙伴
     * @param id
     * @param name
     * @param banner
     * @param link
     */
    public void updatePartnerById(String id,String name,String banner,String link){
        Partner partner = new Partner();
        if (StringUtils.isNotEmpty(id)){
            partner.setId(Integer.parseInt(id));
        }
        if (StringUtils.isNotEmpty(name)){
            partner.setName(name);
        }
        if (StringUtils.isNotEmpty(banner)){
            partner.setBanner(banner);
        }
        if (StringUtils.isNotEmpty(link)){
            partner.setLink(link);
        }
        partnerService.updatePartnerById(partner);
    }


    /**
     * 删除合作伙伴
     * @param id
     */
    public void deletePartner(String id){
        Partner partner = new Partner();
        partner.setId(Integer.parseInt(id));
        partner.setIsdel(1);
        partnerService.deletePartner(partner);
    }
}
