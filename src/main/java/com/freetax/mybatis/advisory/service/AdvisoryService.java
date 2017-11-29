package com.freetax.mybatis.advisory.service;

import com.freetax.mybatis.advisory.entity.Advisory;
import com.freetax.mybatis.advisory.entity.AdvisoryVo;
import com.freetax.mybatis.advisory.mapper.AdvisoryMapper;
import com.freetax.utils.pagination.model.Paging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhurui
 * @Date 2017/11/16 18:13
 */
@Service
public class AdvisoryService {

    private static Logger log = LoggerFactory.getLogger(AdvisoryService.class);

    @Autowired
    private AdvisoryMapper advisoryMapper;

    /**
     * pc咨询
     * @param advisory
     */
    public void insertAdvisory(Advisory advisory){
        try {
            log.info("pc咨询");
            advisoryMapper.insertSelective(advisory);
        } catch (Exception e) {
            log.error("pc咨询异常",e);
            throw e;
        }
    }

    /**
     * 查询咨询详情
     * @param id
     * @return
     */
    public Advisory queryAdvisoryById(Integer id){
        try {
            log.info("查询咨询详情");
            return advisoryMapper.queryAdvisoryById(id);
        } catch (Exception e) {
            log.error("查询咨询详情异常",e);
            throw e;
        }
    }

    /**
     * 查询咨询列表
     * @param advisory
     * @param pag
     * @return
     */
    public List<Advisory> queryAdvisoryByList(AdvisoryVo advisory, Paging<Advisory> pag){
        try {
            log.info("查询咨询列表");
            return advisoryMapper.findAllQueryAdvisoryByList(advisory,pag.getRowBounds());
        } catch (Exception e) {
            log.error("查询咨询列表异常",e);
            throw e;
        }
    }

    /**
     * 修改咨询回访
     * @param advisory
     */
    public void updateAdvisoryVisit(Advisory advisory){
        try {
            log.info("修改咨询回访");
            advisoryMapper.updateAdvisoryVisit(advisory);
        } catch (Exception e) {
            log.error("修改咨询回访异常",e);
            throw e;
        }
    }

    /**
     * 更新咨询时间
     * @param advisory
     */
    public void updateAdvisoryIntime(Advisory advisory){
        try {
            log.info("更新咨询时间");
            advisoryMapper.updateAdvisoryIntime(advisory);
        } catch (Exception e) {
            log.error("更新咨询时间异常",e);
            throw e;
        }
    }

    /**
     *  查询用户是否咨询过
     * @param advisory
     * @return
     */
    public Integer queryAdvisoryIsVisit(Advisory advisory){
        try {
            log.info("查询用户是否咨询过");
            return advisoryMapper.queryAdvisoryIsVisit(advisory);
        } catch (Exception e) {
            log.error("查询用户是否咨询过异常",e);
            throw e;
        }
    }
}
