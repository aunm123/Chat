package com.interceptors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pojo.ResultData;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.*;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object
            o) throws Exception {
        String host = request.getRemoteHost();
        String url = request.getRequestURI();

        HttpSession session = request.getSession(false);
        if (session != null) {
            // 使用userName区分WebSocketHandler，以便定向发送消息
            try {
                String userName = session.getAttribute("SESSION_USERID").toString();
                Integer userid  = Integer.parseInt(userName);
            }catch (Exception e){
                try {
                    String serviceName = session.getAttribute("SESSION_SERVICEID").toString();
                    Integer serviceid  = Integer.parseInt(serviceName);
                }catch (Exception e1){
                    try {
                        String toJSONString = JSON.toJSONString(ResultData.FaileResultData("请先登录",102));
                        response.setContentType("application/json; charset=utf-8");
                        PrintWriter out = null;
                        out = response.getWriter();
                        out.append(toJSONString);
                        out.close();
                    }catch (Exception e2){
                    }
                    return false;
                }

            }
        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object o, Exception e) throws Exception {

    }
}
