package com.service.Impl;

import com.dao.mapper.GroupMapper;
import com.dao.mapper.RoomMapper;
import com.pojo.Group;
import com.pojo.Room;
import com.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("groupservice")
public class GroupServiceImpl implements GroupService {

    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private GroupMapper groupMapper;

    public Room findRoomWithUseridAndGroupid(Integer userid, Integer groupid) {
        Room room = roomMapper.selectByUseridAndGroupId(userid, groupid);
        if (room == null){
            Group group = groupMapper.selectByPrimaryKey(groupid);
            room = new Room();
            room.setUserId(userid);
            room.setGroupId(groupid);
            room.setRoomName(group.getName());
            room.setRoomDescription(group.getName());
            room.setRoomIcon("/img/cvs.jpg");
            roomMapper.insert(room);
        }

        return room;

    }

    public Integer findServiceWithGroupid(Integer userid, Integer groupid) {
        return null;
    }

    public List<Group> findGroupList() {
        List<Group> allGroup = groupMapper.findAllGroup();
        return allGroup;
    }


}
