package com.freetax.mybatis.partner.service;

import com.freetax.mybatis.partner.entity.Partner;
import com.freetax.mybatis.partner.mapper.PartnerMapper;
import com.freetax.utils.pagination.model.Paging;
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


    /**
     * 新增合作伙伴
     * @param partner
     */
    public void insertPartner(Partner partner){
        try {
            log.info("新增合作伙伴");
            partnerMapper.insertSelective(partner);
        } catch (Exception e) {
            log.error("新增合作伙伴异常",e);
            throw e;
        }
    }

    /**
     * 根据id查询合作伙伴详情
     * @param id
     * @return
     */
    public Partner queryPartnerById(Integer id){
        try {
            log.info("根据id查询合作伙伴详情");
            return partnerMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            log.error("根据id查询合作伙伴异常",e);
            throw e;
        }
    }

    /**
     * 条件查询合作伙伴列表
     * @param partner
     * @param pag
     * @return
     */
    public List<Partner> queryPartnerByList(Partner partner , Paging<Partner> pag){
        try {
            log.info("条件查询合作伙伴列表");
            return partnerMapper.findAllQueryPartnerByList(partner,pag.getRowBounds());
        } catch (Exception e) {
            log.error("条件查询合作伙伴列表异常",e);
            throw e;
        }
    }

    /**
     * 修改合作伙伴
     * @param partner
     */
    public void updatePartnerById(Partner partner){
        try {
            log.info("修改合作伙伴");
            partnerMapper.updateByid(partner);
        } catch (Exception e) {
            log.error("溪丢该合作伙伴异常",e);
            throw e;
        }
    }

    /**
     * 删除合作伙伴
     * @param partner
     */
    public void deletePartner(Partner partner){
        try {
            log.info("根据id删除合作伙伴");
            partnerMapper.deletePartner(partner);
        } catch (Exception e) {
            log.error("根据id删除合作伙伴异常",e);
            throw e;
        }
    }
}
