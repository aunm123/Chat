package com.ws.Message;

import com.alibaba.fastjson.JSON;
import com.pojo.Record;

import java.util.HashMap;
import java.util.Map;

public class RecordMessage {
    Record record;
    Integer messageCode = 404;

    public RecordMessage(Record record) {
        this.record = record;
    }

    public String toJsonString(){
        Map map = new HashMap();
        map.put("mcode",messageCode.toString());
        map.put("record", JSON.toJSONString(record));

        return JSON.toJSONString(map);
    }
}
