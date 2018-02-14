package com.service.Impl;

import com.dao.mapper.RecordMapper;
import com.dao.mapper.RoomMapper;
import com.pojo.Record;
import com.pojo.Room;
import com.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roomservice")
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private RoomMapper roomMapper;


    public List<Record> roomhistory(Integer roomid) {
        List<Record> records = recordMapper.selectByRoomId(roomid);
        return records;
    }

    public Room getRoomData(Integer roomid) {
        Room room = roomMapper.selectByPrimaryKey(roomid);
        return room;
    }
}
