package com.service;

import com.pojo.Group;
import com.pojo.Room;
import com.pojo.Service;

import java.util.List;

public interface GroupService {
    public Room findRoomWithUseridAndGroupid(Integer userid,Integer groupid);
    public Integer findServiceWithGroupid(Integer userid,Integer groupid);
    public List<Group> findGroupList();
}
