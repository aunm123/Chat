package com.pojo;

import java.util.HashMap;
import java.util.Map;

public class ResultData {
    private String msg;
    private Integer status;
    private Map<String, Object> result;

    public ResultData() {
    }

    public static ResultData SuccessResultData(Map<String, Object> dataMap) {
        ResultData resultData = new ResultData();
        resultData.msg = "success";
        resultData.status = 0;
        resultData.result = dataMap;
        return resultData;
    }

    public static ResultData ok() {
        ResultData resultData = new ResultData();
        resultData.msg = "success";
        resultData.status = 0;
        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        resultData.result = stringObjectHashMap;
        return resultData;
    }

    public static ResultData FaileResultData(String msg, Integer errorCode) {
        ResultData resultData = new ResultData();
        resultData.msg = msg;
        resultData.status = errorCode;
        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        resultData.result = stringObjectHashMap;
        return resultData;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
