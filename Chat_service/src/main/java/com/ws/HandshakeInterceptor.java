package com.ws;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import java.util.Map;
import org.apache.log4j.*;

public class HandshakeInterceptor  extends HttpSessionHandshakeInterceptor {


    private static Logger logger = Logger.getLogger(HandshakeInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // 解决The extension [x-webkit-deflate-frame] is not supported问题
        if (request.getHeaders().containsKey("Sec-WebSocket-Extensions")){
            request.getHeaders().set("Sec-WebSocket-Extensions","permessage-deflate");
        }

        logger.debug("Before Handshake");
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        logger.debug("After Handshake");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}