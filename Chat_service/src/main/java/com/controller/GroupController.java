package com.controller;

import com.pojo.Group;
import com.pojo.ResultData;
import com.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;


    @RequestMapping("/list")
    @ResponseBody
    public ResultData groupList() {
        List<Group> groups = groupService.findGroupList();
        HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
        stringObjectHashMap.put("groups", groups);
        ResultData resultData = ResultData.SuccessResultData(stringObjectHashMap);
        return resultData;
    }
}
