package com.service.Impl;

import com.dao.mapper.RecordMapper;
import com.dao.mapper.RoomMapper;
import com.dao.mapper.ServiceMapper;
import com.dao.mapper.UserMapper;
import com.pojo.*;
import com.global.ShareGlobal;
import com.service.GroupService;
import com.service.RecordService;
import com.service.ServiceSerivce;
import com.ws.TWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.TextMessage;

@org.springframework.stereotype.Service("serviceservice")
public class ServiceServiceImpl implements ServiceSerivce {

    @Autowired
    private RecordService recordService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private ShareGlobal shareGlobal;
    @Autowired
    private ServiceMapper serviceMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Bean
    public TWebSocketHandler infoHandler() {
        return new TWebSocketHandler();
    }

    public void serviceUser(Integer userid,Integer serviceid,Integer roomid) {

        //设置房间信息
        Room room = roomMapper.selectByPrimaryKey(roomid);

        shareGlobal.getWaiteRoomList().add(roomid);

    }

    public Service loginService(String userName, String password) {
        User user = userMapper.selectByNameAndPassword(userName,password);
        if (user != null && user.getIsservice()){
            Service service = serviceMapper.selectByUserId(user.getId());
            if (service != null){
                shareGlobal.getWaiteServerList().add(service);
            }
            return service;
        }
        return null;
    }

    public void sendMessage(Record record){
        Room room = roomMapper.selectByPrimaryKey(record.getRoomId());

        infoHandler().sendMessageToRoom(room,record);
        recordMapper.insert(record);
    }

}
