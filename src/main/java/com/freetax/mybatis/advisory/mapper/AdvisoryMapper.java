package com.freetax.mybatis.advisory.mapper;

import com.freetax.mybatis.advisory.entity.Advisory;
import com.freetax.mybatis.advisory.entity.AdvisoryVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvisoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advisory record);

    int insertSelective(Advisory record);

    Advisory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Advisory record);

    int updateByPrimaryKey(Advisory record);

    Advisory queryAdvisoryById(Integer id);

    List<Advisory> findAllQueryAdvisoryByList(AdvisoryVo advisory, RowBounds rowBounds);

    void updateAdvisoryVisit(Advisory advisory);

    void updateAdvisoryIntime(Advisory advisory);

    Integer queryAdvisoryIsVisit(Advisory advisory);

    List<Advisory> queryUserByAll();
}