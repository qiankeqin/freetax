package com.freetax.mybatis.advisory.service;

import com.freetax.mybatis.advisory.entity.Advisory;
import com.freetax.mybatis.advisory.mapper.AdvisoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
