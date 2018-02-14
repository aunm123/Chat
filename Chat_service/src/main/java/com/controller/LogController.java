package com.controller;

import com.pojo.Room;
import com.pojo.Service;
import com.pojo.User;
import com.pojo.ResultData;
import com.service.GroupService;
import com.service.ServiceSerivce;
import com.service.UserService;
import com.ws.TWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;


@Controller
@RequestMapping("/account")
public class LogController {

    @Autowired
    private UserService userService;
    @Autowired
    private ServiceSerivce serviceSerivce;

    @RequestMapping("/login")
    @ResponseBody
    public ResultData login(HttpSession session, String userName, String password) {
        User user = userService.loginUser(userName, password);
        if (user != null) {
            HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
            stringObjectHashMap.put("user", user);
            ResultData resultData = ResultData.SuccessResultData(stringObjectHashMap);
            session.setAttribute("SESSION_USERID", user.getId());
            return resultData;
        } else {
            ResultData resultData = ResultData.FaileResultData("登录失败！", 411);
            return resultData;
        }

    }
    @RequestMapping("/slogin")
    @ResponseBody
    public ResultData slogin(HttpSession session,String userName, String password) {
        Service service = serviceSerivce.loginService(userName,password);
        if (service != null) {
            HashMap<String, Object> stringObjectHashMap = new HashMap<String, Object>();
            stringObjectHashMap.put("service", service);
            session.setAttribute("SESSION_USERID", service.getId());
            ResultData resultData = ResultData.SuccessResultData(stringObjectHashMap);
            return resultData;
        } else {
            ResultData resultData = ResultData.FaileResultData("登录失败！", 411);
            return resultData;
        }
    }

}
