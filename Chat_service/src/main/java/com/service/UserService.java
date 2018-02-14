package com.service;

import com.pojo.Record;
import com.pojo.User;

public interface UserService {
    public User loginUser(String userName, String password);
    public void sendMessage(String content,Integer userid, Integer roomid);
    public User findUserById(Integer userid);
}
