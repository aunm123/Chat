package com.service.Impl;

import com.dao.mapper.RecordMapper;
import com.dao.mapper.RoomMapper;
import com.dao.mapper.UserMapper;
import com.pojo.Record;
import com.pojo.Room;
import com.pojo.User;
import com.global.ShareGlobal;
import com.service.UserService;
import com.ws.TWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.util.Date;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private ShareGlobal shareGlobal;

    @Bean
    public TWebSocketHandler infoHandler() {
        return new TWebSocketHandler();
    }

    public User loginUser(String userName, String password) {
        User user = userMapper.selectByNameAndPassword(userName, password);
        return user;
    }

    public void sendMessage(String content,Integer userid,Integer roomid) {

        Room room = roomMapper.selectByPrimaryKey(roomid);

        Record record = new Record();
        record.setContent(content);
        record.setFromId(userid);
        record.setRoomId(roomid);
        record.setCreatetime(new Date());

        infoHandler().sendMessageToRoom(room,record);
        recordMapper.insert(record);

    }

    public User findUserById(Integer userid) {
        return userMapper.selectByPrimaryKey(userid);

    }


}
