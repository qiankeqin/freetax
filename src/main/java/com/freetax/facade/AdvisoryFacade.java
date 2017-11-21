package com.freetax.facade;

import com.freetax.mybatis.advisory.entity.Advisory;
import com.freetax.mybatis.advisory.service.AdvisoryService;
import com.freetax.utils.pagination.model.Paging;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
     *
     * @param phone
     */
    public void insertAdvisory(String phone,String name) {
        Advisory advisory = new Advisory();
        advisory.setPhone(phone);
        advisory.setName(name);
        //查询用户是否资讯过，资讯过并且没有回访的话需要更新时间，并不新增
        Integer is = advisoryService.queryAdvisoryIsVisit(advisory);
        if (is != null) {
            advisory.setIntime(new Date());
            advisory.setId(is);
            advisoryService.updateAdvisoryIntime(advisory);
        } else {
            advisory.setVisit(0);
            advisory.setIntime(new Date());
            advisoryService.insertAdvisory(advisory);
        }
    }

    /**
     * 查询咨询详情
     *
     * @param id
     * @return
     */
    public Advisory queryAdvisoryById(String id) {
        return advisoryService.queryAdvisoryById(Integer.parseInt(id));
    }

    /**
     * 查询咨询列表
     *
     * @param phone
     * @param pag
     * @return
     */
    public List<Advisory> queryAdvisoryByList(String phone, String visit, Paging<Advisory> pag) {
        Advisory advisory = new Advisory();
        if (StringUtils.isNotEmpty(phone)) {
            advisory.setPhone(phone);
        }
        if (StringUtils.isNotEmpty(visit)) {
            advisory.setVisit(Integer.parseInt(visit));
        }
        return advisoryService.queryAdvisoryByList(advisory, pag);
    }

    /**
     * 修改咨询回访
     *
     * @param id
     * @param visit
     */
    public void updateAdvisoryVisit(String id, String visit) {
        Advisory advisory = new Advisory();
        advisory.setId(Integer.parseInt(id));
        advisory.setVisit(Integer.parseInt(visit));
        advisoryService.updateAdvisoryVisit(advisory);
    }
}
