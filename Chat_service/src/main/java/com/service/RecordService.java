package com.service;


import com.pojo.Group;
import com.pojo.Record;
import com.pojo.Service;
import com.pojo.User;

public interface RecordService {

    /**
     * 创建用户发送的聊天记录
     * @param content
     * @param userid
     * @param roomid
     * @return
     */
    public Record createUserRecord(String content, Integer userid, Integer roomid );

    /**
     * 创建客服发送的聊天记录
     * @param content
     * @param serviceid
     * @param roomid
     * @return
     */
    public Record createServiceRecord(String content, Integer serviceid, Integer roomid );

    /**
     * 创建系统发送的聊天记录
     * @param content
     * @param roomid
     * @return
     */
    public Record createSystemRecord(String content, Integer roomid );
}
