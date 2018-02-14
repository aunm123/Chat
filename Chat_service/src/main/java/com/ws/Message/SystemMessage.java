package com.ws.Message;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class SystemMessage {
    Integer messageCode = 401;
    String content;

    public SystemMessage(String content) {
        this.content = content;
    }

    public String toJsonString(){
        Map map = new HashMap();
        map.put("mcode",messageCode.toString());
        map.put("content", JSON.toJSONString(content));

        return JSON.toJSONString(map);
    }
}
