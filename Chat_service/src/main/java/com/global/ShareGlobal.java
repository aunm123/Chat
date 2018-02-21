package com.global;

import com.dao.mapper.RecordMapper;
import com.dao.mapper.RoomMapper;
import com.pojo.*;
import com.dao.mapper.ServiceMapper;
import com.dao.mapper.UserMapper;
import com.pojo.Service;
import com.service.RecordService;
import com.service.ServiceSerivce;
import com.ws.TWebSocketHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class ShareGlobal implements InitializingBean {

    @Autowired
    private RoomMapper roomMapper;

    @Bean
    public TWebSocketHandler infoHandler() {
        return new TWebSocketHandler();
    }

    @Autowired
    private RecordService recordService;
    @Autowired
    private RecordMapper recordMapper;

    private List<Integer> waiteRoomList = new LinkedList<Integer>();
    private List<Service> waiteServerList = new LinkedList<Service>();

    public List<Integer> getWaiteRoomList() {
        return waiteRoomList;
    }

    public void setWaiteRoomList(List<Integer> waiteRoomList) {
        this.waiteRoomList = waiteRoomList;
    }

    public List<Service> getWaiteServerList() {
        return waiteServerList;
    }

    public void setWaiteServerQueue(List<Service> waiteServerList) {
        this.waiteServerList = waiteServerList;
    }

    public void afterPropertiesSet() throws Exception {

        Thread thread = new Thread(new Runnable() {

            @Transactional
            public void run() {
                while (true) {
                    Integer roomid = null;
                    if (waiteRoomList.size() > 0) {
                        roomid = waiteRoomList.get(0);
                        if (roomid != null) {
                            Service target = null;
                            Room room = roomMapper.selectByPrimaryKey(roomid);
                            Integer groupId = room.getGroupId();
                            for (int i = 0; i < waiteServerList.size(); i++) {
                                Service service = waiteServerList.get(i);
                                if (groupId.equals(service.getGroupId())) {
                                    target = service;
                                    waiteServerList.remove(service);
                                    waiteRoomList.remove(roomid);
                                    break;
                                }
                            }
                            if (target != null) {
                                room.setServiceId(target.getId());
                                roomMapper.updateByPrimaryKey(room);

                                Record systemRecord = recordService.createSystemRecord("匹配成功 user:" + room.getUserId
                                        () + " room:", room.getId());
                                infoHandler().sendMessageToRoomRoot(room, systemRecord);
                                recordMapper.insert(systemRecord);
                            }
                        }
                    } else {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
        thread.start();
    }
}
