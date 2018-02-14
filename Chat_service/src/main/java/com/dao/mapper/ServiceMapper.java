package com.dao.mapper;

import com.pojo.Service;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Service record);

    int insertSelective(Service record);

    Service selectByPrimaryKey(Integer id);

    Service selectByUserId(Integer userid);

    int updateByPrimaryKeySelective(Service record);

    int updateByPrimaryKey(Service record);
}