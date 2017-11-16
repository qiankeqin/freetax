package com.freetax.facade;

import com.freetax.mybatis.advisory.entity.Advisory;
import com.freetax.mybatis.advisory.service.AdvisoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author zhurui
 * @Date 2017/11/16 18:10
 */
@Service
public class AdvisoryFacade {

    @Autowired
    private AdvisoryService advisoryService;

    /**
     * 咨询
     * @param phone
     */
    public void insertAdvisory(String phone){
        Advisory advisory = new Advisory();
        advisory.setPhone(phone);
        advisory.setVisit(0);
        advisory.setIntime(new Date());
        advisoryService.insertAdvisory(advisory);
    }
}
