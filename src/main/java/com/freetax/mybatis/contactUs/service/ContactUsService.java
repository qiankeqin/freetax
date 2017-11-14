package com.freetax.mybatis.contactUs.service;

import com.freetax.mybatis.contactUs.entity.ContactUs;
import com.freetax.mybatis.contactUs.mapper.ContactUsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zhurui
 * @Date 2017/11/14 10:16
 */
@Service
public class ContactUsService {

    private static Logger log = LoggerFactory.getLogger(ContactUsService.class);

    @Autowired
    private ContactUsMapper contactUsMapper;

    public void insertContactUs(ContactUs contactUs){
        try {
            log.info("新增公司信息");
            contactUsMapper.insertSelective(contactUs);
        } catch (Exception e) {
            log.error("新增公司信息异常",e);
            throw e;
        }
    }

    /**
     * 根据id查询公司详情
     * @param id
     * @return
     */
    public ContactUs queryContactUsDetailsById(Integer id){
        try {
            log.info("根据id查询公司信息详情");
            return contactUsMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            log.error("根据id查询公司详情异常",e);
            throw e;
        }
    }

    /**
     * 修改公司信息
     * @param contactUs
     */
    public void updateContactUsDetails(ContactUs contactUs){
        try {
            log.info("修改公司信息");
            contactUsMapper.updateByPrimaryKeySelective(contactUs);
        } catch (Exception e) {
            log.error("修改公司信息异常",e);
            throw e;
        }
    }

    /**
     * 删除公司信息
     * @param id
     */
    public void  deleteContactUs(Integer id){
        try {
            log.info("删除公司信息");
            contactUsMapper.deleteContactUs(id);
        } catch (Exception e) {
            log.error("删除公司信息异常",e);
            throw e;
        }
    }
}
