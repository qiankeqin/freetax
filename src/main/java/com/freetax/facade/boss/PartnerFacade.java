package com.freetax.facade.boss;

import com.freetax.mybatis.partner.entity.Partner;
import com.freetax.mybatis.partner.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
