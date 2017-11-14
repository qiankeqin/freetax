package com.freetax.mybatis.information.service;

import com.freetax.mybatis.information.entity.Information;
import com.freetax.mybatis.information.mapper.InformationMapper;
import com.freetax.utils.pagination.model.Paging;
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
     *
     * @return
     */
    public List<Information> queryInformationIsHot() {
        try {
            log.info("查询精选的资讯");
            return informationMapper.queryInformationIsHot();
        } catch (Exception e) {
            log.error("查询精选资讯异常", e);
            throw e;
        }
    }

    /**
     * 新增资讯
     *
     * @param information
     */
    public void insertInformation(Information information) {
        try {
            log.info("新增资讯");
            informationMapper.insertSelective(information);
        } catch (Exception e) {
            log.error("新增资讯异常", e);
            throw e;
        }
    }

    /**
     * 修改资讯文章
     *
     * @param information
     */
    public void updateInformation(Information information) {
        try {
            log.info("修改资讯文章");
            informationMapper.updateByPrimaryKeySelective(information);
        } catch (Exception e) {
            log.error("修改资讯文章异常", e);
            throw e;
        }
    }

    /**
     * 删除资讯文章
     *
     * @param id
     */
    public void deleteInformation(Integer id) {
        try {
            log.info("删除资讯文章");
            informationMapper.deleteInformation(id);
        } catch (Exception e) {
            log.error("删除资讯文章异常", e);
            throw e;
        }
    }

    /**
     * 条件查询资讯文章列表
     *
     * @param information
     * @param pag
     * @return
     */
    public List<Information> findAllQueryInformationByList(Information information, Paging<Information> pag) {
        try {
            log.info("条件查询资讯文章列表");
            return informationMapper.findAllQueryInformationByList(information, pag.getRowBounds());
        } catch (Exception e) {
            log.error("条件查询资讯文章列表异常", e);
            throw e;
        }
    }

    /**
     * 根据id查询资讯文章详情
     *
     * @param id
     * @return
     */
    public Information queryInformationById(Integer id) {
        try {
            log.info("根据id查询资讯文章详情");
            return informationMapper.queryInformationById(id);
        } catch (Exception e) {
            log.error("根据id查询资讯文章详情异常", e);
            throw e;
        }
    }

    /**
     * 资讯文章设为精选/取消精选
     *
     * @param information
     */
    public void insertInformationByIsHot(Information information) {
        try {
            log.info("资讯文章设为精选/取消精选");
            informationMapper.insertInformationByIsHot(information);
        } catch (Exception e) {
            log.error("资讯文章设为精选/取消精选异常", e);
            throw e;
        }
    }
}
