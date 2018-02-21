package com.controller;

import com.dao.mapper.RoomMapper;
import com.dao.mapper.UserMapper;
import com.global.ShareGlobal;
import com.pojo.ResultData;
import com.pojo.Room;
import com.pojo.User;
import com.ws.TWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/total")
public class ServiceTotal {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private ShareGlobal shareGlobal;

    @Bean
    public TWebSocketHandler infoHandler() {
        return new TWebSocketHandler();
    }

    @RequestMapping("/onlineuserlist")
    @ResponseBody
    public ResultData onlineuserlist() {

        ArrayList<WebSocketSession> users = TWebSocketHandler.getUsers();
        ArrayList<User> users1 = new ArrayList<User>();

        for (WebSocketSession session : users) {
            String useridStr = session.getAttributes().get("SESSION_USERID").toString();
            Integer userid = Integer.parseInt(useridStr);

            User user = userMapper.selectByPrimaryKey(userid);
            user.setPassword("");
            users1.add(user);
        }
        User user = userMapper.selectByPrimaryKey(1);
        user.setPassword("");
        users1.add(user);

        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("users", users1);
        ResultData resultData = ResultData.SuccessResultData(stringObjectHashMap);
        return resultData;
    }

    @RequestMapping("/userlist")
    @ResponseBody
    public ResultData userlist() {

        List<User> allUser = userMapper.findAllUser();
        ArrayList<User> users1 = new ArrayList<User>();
        for (User user : allUser) {
            if(!user.getIsservice()){
                users1.add(user);
            }
        }

        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("users", users1);
        ResultData resultData = ResultData.SuccessResultData(stringObjectHashMap);
        return resultData;
    }

    @RequestMapping("/waiteuserlist")
    @ResponseBody
    public ResultData waiteuserlist() {

        List<Integer> waiteRoomList = shareGlobal.getWaiteRoomList();
        ArrayList<User> users1 = new ArrayList<User>();
        for (Integer roomid : waiteRoomList) {
            Room room = roomMapper.selectByPrimaryKey(roomid);
            User user = userMapper.selectByPrimaryKey(room.getUserId());
            users1.add(user);
        }

        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("users", users1);
        ResultData resultData = ResultData.SuccessResultData(stringObjectHashMap);
        return resultData;
    }

}
