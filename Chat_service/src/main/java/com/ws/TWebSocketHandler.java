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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;

public class TWebSocketHandler extends TextWebSocketHandler {

    // 已建立连接的用户
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();

    /**
     * 处理前端发送的文本信息 js调用websocket.send时候，会调用该方法
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String username = session.getAttributes().get("SESSION_USERID").toString();
        // 获取提交过来的消息详情
        System.out.println("user: " + username + "\n message:" + message.toString());
        // 回复一条信息，
//		session.sendMessage(new TextMessage("reply msg:" + message.getPayload()));
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
        users.add(session);
        String username = session.getAttributes().get("SESSION_USERID").toString();
        System.out.println("user: " + username + " Connectione Established");

        SystemMessage systemMessage = new SystemMessage(username + " connect");
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
        String username = session.getAttributes().get("SESSION_USERID").toString();
        System.out.println("user: " + username + " Connection closed. Status: " + status);
        users.remove(session);
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
        String username = session.getAttributes().get("SESSION_USERID").toString();
        if (session.isOpen()) {
            session.close();
        }
        System.out.println("用户: " + username + " websocket connection closed......");

        users.remove(session);
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        System.out.println("all sendMessageToUsers :" + message);
        for (WebSocketSession user : users) {
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
        System.out.println("one sendMessageToUser :" + message);
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("SESSION_USERID").equals(userName)) {
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
        System.out.println("one sendMessageToRoom :" + record.getContent());

        Integer fromid = null;
        if (record.getFromId() != null) {
            fromid = record.getFromId();
        } else if (record.getFromSid() != null) {
            fromid = record.getFromSid();
        }
        for (WebSocketSession user : users) {
            String username = user.getAttributes().get("SESSION_USERID").toString();
            Integer userId = Integer.parseInt(username);
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
            System.out.println("one sendMessageToRoom :" + record.getContent());

            for (WebSocketSession user : users) {
                String username = user.getAttributes().get("SESSION_USERID").toString();
                if (username.equals(room.getUserId().toString()) || username.equals(room.getServiceId().toString())) {
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
