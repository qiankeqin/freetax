package com.freetax.mybatis.partner.service;

import com.freetax.mybatis.partner.entity.Partner;
import com.freetax.mybatis.partner.mapper.PartnerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhurui
 * @Date 2017/11/14 16:39
 */
@Service
public class PartnerService {

    private static Logger log = LoggerFactory.getLogger(PartnerService.class);

    @Autowired
    private PartnerMapper partnerMapper;

    /**
     * 查询首页合作伙伴列表
     * @return
     */
    public List<Partner> queryPartnerList(){
        try {
            log.info("查询首页合作伙伴列表");
            return partnerMapper.queryPartnerList();
        } catch (Exception e) {
            log.error("查询首页合作伙伴列表异常",e);
            throw e;
        }
    }
}
