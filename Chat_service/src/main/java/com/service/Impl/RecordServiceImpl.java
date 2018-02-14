package com.service.Impl;

import com.dao.mapper.RoomMapper;
import com.pojo.Group;
import com.pojo.Record;
import com.pojo.Room;
import com.pojo.User;
import com.service.RecordService;
import com.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@SuppressWarnings("ALL")
@Service("recordservice")
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RoomMapper roomMapper;

    public Record createUserRecord(String content, Integer userid, Integer roomid) {

        Room room = roomMapper.selectByPrimaryKey(roomid);
        Record record = new Record();
        record.setContent(content);
        record.setRoomId(room.getId());
        record.setFromId(userid);
        return record;
    }

    public Record createServiceRecord(String content, Integer serviceid, Integer roomid) {
        Room room = roomMapper.selectByPrimaryKey(roomid);
        Record record = new Record();
        record.setContent(content);
        record.setRoomId(room.getId());
        record.setFromSid(serviceid);
        return record;
    }

    public Record createSystemRecord(String content, Integer roomid) {
        Room room = roomMapper.selectByPrimaryKey(roomid);
        Record record = new Record();
        record.setContent(content);
        record.setRoomId(room.getId());
        return record;
    }
}
