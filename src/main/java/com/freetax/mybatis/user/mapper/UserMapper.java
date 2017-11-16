package com.freetax.mybatis.user.mapper;

import com.freetax.mybatis.user.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    void updateLoginappuserInfo(User user);

    void updatePasswd(Map<String, Object> parammap);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    LoginUser selectLoginUserByToken(Map<String, Object> parammap);

    Integer queryIsRegister(String mobile);

    LoginUser selectLoginuserByUserid(@Param("phone") String mobile);

}