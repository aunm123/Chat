package com.ws;

import com.dao.mapper.RecordMapper;
import com.dao.mapper.RoomMapper;
import com.dao.mapper.ServiceMapper;
import com.dao.mapper.UserMapper;
import com.pojo.Record;
import com.pojo.Room;
import com.pojo.Service;
import com.pojo.User;
import com.ws.Message.RecordMessage;
import com.ws.Message.SystemMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;

public class TWebSocketHandler extends TextWebSocketHandler {

    private static Logger logger = Logger.getLogger(TWebSocketHandler.class);

    // 已建立连接的用户(包括客服)
    private static final ArrayList<WebSocketSession> usersAndservice = new ArrayList<WebSocketSession>();
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();
    private static final ArrayList<WebSocketSession> service = new ArrayList<WebSocketSession>();

    public static ArrayList<WebSocketSession> getUsersAndservice() {
        return usersAndservice;
    }

    public static ArrayList<WebSocketSession> getUsers() {
        return users;
    }

    public static ArrayList<WebSocketSession> getService() {
        return service;
    }

    private String getNameFromSession(WebSocketSession session){
        try {
            String username = session.getAttributes().get("SESSION_USERID").toString();
            return username;
        } catch (Exception e){
            try {
                String servicername = session.getAttributes().get("SESSION_SERVICEID").toString();
                return servicername;
            } catch (Exception e1){
                return "";
            }
        }
    }

    private Boolean isUser(WebSocketSession session){
        try {
            String username = session.getAttributes().get("SESSION_USERID").toString();
            return true;
        } catch (Exception e){
            try {
                String servicername = session.getAttributes().get("SESSION_SERVICEID").toString();
                return false;
            } catch (Exception e1){
                return false;
            }
        }
    }

    /**
     * 处理前端发送的文本信息 js调用websocket.send时候，会调用该方法
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 获取提交过来的消息详情
        logger.debug("user: " + this.getNameFromSession(session) + "\n message:" + message.toString());
        // 回复一条信息，
        sendMessageToUsers(message);
    }

    /**
     * 当新连接建立的时候，被调用 连接成功时候，会触发页面上onOpen方法
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        usersAndservice.add(session);
        if (this.isUser(session)){
            users.add(session);
        }else {
            service.add(session);
        }
        String name = this.getNameFromSession(session);
        System.out.println("user: " + name + " Connectione Established");
        SystemMessage systemMessage = new SystemMessage(name + " connect");
        session.sendMessage(new TextMessage(systemMessage.toJsonString()));
    }

    /**
     * 当连接关闭时被调用
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String name = this.getNameFromSession(session);
        System.out.println("user: " + name + " Connection closed. Status: " + status);
        usersAndservice.remove(session);
        if (this.isUser(session)){
            users.remove(session);
        }else {
            service.remove(session);
        }
    }

    /**
     * 传输错误时调用
     *
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        String name = this.getNameFromSession(session);
        if (session.isOpen()) {
            session.close();
        }
        logger.debug("用户: " + name + " websocket connection closed......");

        usersAndservice.remove(session);
        if (this.isUser(session)){
            users.remove(session);
        }else {
            service.remove(session);
        }
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {

        logger.debug("all sendMessageToUsers :" + message);

        for (WebSocketSession user : usersAndservice) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {

        logger.debug("one sendMessageToUser :" + message);

        for (WebSocketSession user : usersAndservice) {
            String name = this.getNameFromSession(user);
            if (name.equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * 给某个房间发送消息
     *
     * @param room
     * @param record
     */
    @SuppressWarnings("Duplicates")
    public void sendMessageToRoom(Room room, Record record) {

        logger.debug("one sendMessageToRoom :" + record.getContent());

        Integer fromid = null;
        if (record.getFromId() != null) {
            fromid = record.getFromId();
        } else if (record.getFromSid() != null) {
            fromid = record.getFromSid();
        }
        for (WebSocketSession user : usersAndservice) {
            String name = this.getNameFromSession(user);
            Integer userId = Integer.parseInt(name);
            if (userId ==room.getUserId() || userId == room.getServiceId()) {
                try {
                    if (user.isOpen()) {
                        RecordMessage recordMessage = new RecordMessage(record);
                        user.sendMessage(new TextMessage(recordMessage.toJsonString()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    /**
     * 系统给某个房间发送消息
     *
     * @param record
     */
    @SuppressWarnings("Duplicates")
    public void sendMessageToRoomRoot(Room room, Record record) {
        try {
            logger.debug("sys sendMessageToRoom :" + record.getContent());

            for (WebSocketSession user : usersAndservice) {
                String name = this.getNameFromSession(user);
                if (name.equals(room.getUserId().toString()) || name.equals(room.getServiceId().toString())) {
                    if (user.isOpen()) {
                        RecordMessage recordMessage = new RecordMessage(record);
                        user.sendMessage(new TextMessage(recordMessage.toJsonString()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
