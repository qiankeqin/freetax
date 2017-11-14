package com.freetax.mybatis.contactUs.mapper;

import com.freetax.mybatis.contactUs.entity.ContactUs;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactUsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContactUs record);

    int insertSelective(ContactUs record);

    ContactUs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContactUs record);

    int updateByPrimaryKey(ContactUs record);

    void deleteContactUs(Integer id);
}