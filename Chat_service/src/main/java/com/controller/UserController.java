package com.controller;

import com.dao.mapper.RecordMapper;
import com.dao.mapper.RoomMapper;
import com.pojo.Record;
import com.pojo.ResultData;
import com.pojo.Room;
import com.pojo.Service;
import com.service.GroupService;
import com.service.RecordService;
import com.service.ServiceSerivce;
import com.service.UserService;
import com.util.Token;
import com.ws.TWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ServiceSerivce serviceSerivce;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private GroupService groupService;
    @Autowired
    private RecordMapper recordMapper;

    @Bean
    public TWebSocketHandler infoHandler() {
        return new TWebSocketHandler();
    }

    @RequestMapping("/joinroom")
    @ResponseBody
    public ResultData joinroom(Integer userid, Integer groupid) {

        Room room = groupService.findRoomWithUseridAndGroupid(userid, groupid);
        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("room", room);
        ResultData resultData = ResultData.SuccessResultData(stringObjectHashMap);
        return resultData;
    }

    @RequestMapping("/reservice")
    @ResponseBody
    public ResultData reservice(Integer roomid, Integer userid) {

        Room room = roomMapper.selectByPrimaryKey(roomid);
        Integer serviceid = room.getServiceId();

        serviceSerivce.serviceUser(userid,serviceid,roomid);

        Record record = new Record();
        record.setContent("正在为您匹配客服，请稍后....");
        record.setRoomId(roomid);
        record.setCreatetime(new Date());

        recordMapper.insert(record);
        infoHandler().sendMessageToRoom(room, record);

        return ResultData.ok();
    }

    @RequestMapping("/roomlist")
    @ResponseBody
    public ResultData roomlist(Integer userid) {

        List<Room> rooms = roomMapper.selectByUserid(userid);

        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("roomlist", rooms);
        ResultData resultData = ResultData.SuccessResultData(stringObjectHashMap);
        return resultData;
    }

    @RequestMapping("/sendmsg")
    @ResponseBody
    public ResultData sendMessage(String msg, Integer roomid) {

        Room room = roomMapper.selectByPrimaryKey(roomid);

        Record record = new Record();
        record.setContent(msg);
        record.setRoomId(roomid);
        record.setFromId(room.getUserId());
        record.setCreatetime(new Date());

        recordMapper.insert(record);
        infoHandler().sendMessageToRoom(room, record);

        return ResultData.ok();
    }
}
