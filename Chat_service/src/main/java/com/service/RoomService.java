package com.service;

import com.pojo.Record;
import com.pojo.Room;

import java.util.List;

public interface RoomService {

    /**
     * 获取房间的聊天记录
     * @param roomid
     * @return
     */
    public List<Record> roomhistory(Integer roomid);


    public Room getRoomData(Integer roomid);

}
