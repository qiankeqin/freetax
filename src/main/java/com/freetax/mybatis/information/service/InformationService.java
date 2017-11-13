package com.freetax.mybatis.information.service;

import com.freetax.mybatis.information.entity.Information;
import com.freetax.mybatis.information.mapper.InformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhurui
 * @Date 2017/11/13 10:52
 */
@Service
public class InformationService {

    private static Logger log = LoggerFactory.getLogger(InformationService.class);

    @Autowired
    private InformationMapper informationMapper;

    /**
     * 查询精选资讯
     * @return
     */
    public List<Information> queryInformationIsHot(){
        try {
            log.info("查询精选的资讯");
            return informationMapper.queryInformationIsHot();
        } catch (Exception e) {
            log.error("查询精选资讯异常",e);
            throw e;
        }
    }
}
