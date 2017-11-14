package com.freetax.facade;

import com.freetax.mybatis.contactUs.entity.ContactUs;
import com.freetax.mybatis.contactUs.service.ContactUsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author zhurui
 * @Date 2017/11/14 10:11
 */
@Service
public class ContactUsFacade {

    @Autowired
    private ContactUsService contactUsService;

    /**
     * 新增公司信息
     * @param name
     * @param subsidiary
     * @param phone
     * @param site
     * @param banner
     */
    public void insertContactUs(String name,String subsidiary,String phone,String site,String banner){
        ContactUs contactUs = new ContactUs();
        if (StringUtils.isNotEmpty(name)){
            contactUs.setName(name);
        }
        if (StringUtils.isNotEmpty(subsidiary)){
            contactUs.setSubsidiary(subsidiary);
        }
        if (StringUtils.isNotEmpty(phone)){
            contactUs.setPhone(Integer.parseInt(phone));
        }
        if (StringUtils.isNotEmpty(site)){
            contactUs.setSite(site);
        }
        if (StringUtils.isNotEmpty(banner)){
            contactUs.setBanner(banner);
        }
        contactUs.setIntime(new Date());
        contactUs.setIsdel(0);
        contactUsService.insertContactUs(contactUs);
    }

    /**
     * 查询公司信息
     * @param id
     * @return
     */
    public ContactUs queryContactUsDetailsById(String id){
        return contactUsService.queryContactUsDetailsById(Integer.parseInt(id));
    }

    /**
     * 修改公司信息
     * @param id
     * @param name
     * @param subsidiary
     * @param phone
     * @param site
     * @param banner
     */
    public void updateContactUsDetails(String id,String name,String subsidiary,String phone,String site,String banner){
        ContactUs contactUs = new ContactUs();
        if (StringUtils.isNotEmpty(id)){
            contactUs.setId(Integer.parseInt(id));
        }
        if (StringUtils.isNotEmpty(name)){
            contactUs.setName(name);
        }
        if (StringUtils.isNotEmpty(subsidiary)){
            contactUs.setSubsidiary(subsidiary);
        }
        if (StringUtils.isNotEmpty(phone)){
            contactUs.setPhone(Integer.parseInt(phone));
        }
        if (StringUtils.isNotEmpty(site)){
            contactUs.setSite(site);
        }
        if (StringUtils.isNotEmpty(banner)){
            contactUs.setBanner(banner);
        }
        contactUsService.updateContactUsDetails(contactUs);
    }

    /**
     * 删除公司信息
     * @param id
     */
    public void deleteContactUs(String id){
        contactUsService.deleteContactUs(Integer.parseInt(id));
    }
}
