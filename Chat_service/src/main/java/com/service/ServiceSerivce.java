package com.service;

import com.pojo.Record;
import com.pojo.Service;
import com.pojo.User;

public interface ServiceSerivce {
    /**
     * 设置房间客服
     * @param userid
     * @param serviceid （null即是自动设置，有传优先有传的客服）
     * @param roomid
     */
    public void serviceUser(Integer userid,Integer serviceid,Integer roomid);

    /**
     * 客服发送聊天消息
     * @param record
     */
    public void sendMessage(Record record);

    /**
     * 客服登录
     * @param userName
     * @param password
     * @return
     */
    public Service loginService(String userName, String password);
}
