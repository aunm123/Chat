package com.controller;

import com.dao.mapper.RoomMapper;
import com.pojo.Record;
import com.pojo.ResultData;
import com.pojo.Room;
import com.service.RoomService;
import com.service.ServiceSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/history")
    @ResponseBody
    public ResultData roomhistory(Integer roomid) {

        List<Record> records = roomService.roomhistory(roomid);

        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("records", records);
        ResultData resultData = ResultData.SuccessResultData(stringObjectHashMap);
        return resultData;
    }

    @RequestMapping("/getdata")
    @ResponseBody
    public ResultData getdata(Integer roomid) {

        Room room = roomService.getRoomData(roomid);

        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("room", room);
        ResultData resultData = ResultData.SuccessResultData(stringObjectHashMap);
        return resultData;
    }

}
