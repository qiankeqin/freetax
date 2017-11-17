package com.freetax.mybatis.user.mapper;

import com.freetax.mybatis.user.entity.*;
import com.freetax.utils.pagination.model.Paging;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    void updateLoginappuserInfo(User user);

    void updatePasswd(Map<String, Object> parammap);

    User selectByPrimaryKey(Integer id);

    User queryUserById(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    LoginUser selectLoginUserByToken(Map<String, Object> parammap);

    Integer queryIsRegister(String mobile);

    LoginUser selectLoginuserByUserid(@Param("phone") String mobile);

    void deleteUserById(Integer id);

    void updateUserByMark(User user);

    List<User> findAllQueryUserByList(UserVo user, RowBounds rowBounds);

}