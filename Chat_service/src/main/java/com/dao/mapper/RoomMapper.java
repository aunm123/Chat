package com.dao.mapper;

import com.pojo.Room;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer id);

    Room selectByUseridAndGroupId(@Param(value = "userid")Integer userid,
                                  @Param(value = "groupid")Integer groupid);

    List<Room> selectByUserid(@Param(value = "userid")Integer userid);

    List<Room> selectByServiceid(@Param(value = "serviceid")Integer serviceid);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);
}