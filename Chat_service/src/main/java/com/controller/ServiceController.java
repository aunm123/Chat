package com.controller;

import com.dao.mapper.RecordMapper;
import com.dao.mapper.RoomMapper;
import com.pojo.Record;
import com.pojo.ResultData;
import com.pojo.Room;
import com.ws.TWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RecordMapper recordMapper;

    @Bean
    public TWebSocketHandler infoHandler() {
        return new TWebSocketHandler();
    }

    @RequestMapping("/sendmsg")
    @ResponseBody
    public ResultData sendMessage(String msg, Integer roomid, Integer serviceid) {

        Room room = roomMapper.selectByPrimaryKey(roomid);

        Record record = new Record();
        record.setContent(msg);
        record.setRoomId(roomid);
        record.setFromSid(serviceid);
        record.setCreatetime(new Date());

        recordMapper.insert(record);
        infoHandler().sendMessageToRoom(room, record);

        return ResultData.ok();
    }

}
