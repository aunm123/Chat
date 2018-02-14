package com.dao.mapper;

import com.pojo.Record;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer id);

    List<Record> selectByRoomId(Integer roomid);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);
}