package com.freetax.mybatis.advisory.mapper;

import com.freetax.mybatis.advisory.entity.Advisory;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advisory record);

    int insertSelective(Advisory record);

    Advisory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Advisory record);

    int updateByPrimaryKey(Advisory record);
}