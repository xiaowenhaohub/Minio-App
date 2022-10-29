package com.vip.file.controller;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/chat")
@Component
public class WebSocketEndpoint {

    static Log log = LogFactory.get(WebSocketEndpoint.class);
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    private String username;

    @OnOpen
    public void onOpen(Session session,  EndpointConfig config){
        this.username = String.valueOf(sessionMap.size() + 1);
        log.info("用户连接:"+username);
        if(!sessionMap.containsKey(username)){
            sessionMap.put(username, session);
        }
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        log.info("发送消息: {}", message);
        sendAllMessage(message);
    }

    @OnClose
    public void onClose() {
        sessionMap.remove(username);
        log.info("用户"+username+"断开连接");

    }

    @OnError
    public void onError(Throwable throwable) {
        sessionMap.remove(username);
        log.info("用户"+username+"断开连接");
        System.out.println(throwable);
        log.info("发生错误");
    }

    public void sendAllMessage(String message) throws IOException {
        for (Object key : sessionMap.keySet()) {
            sessionMap.get(key).getBasicRemote().sendText(message);
        }

    }

    public void sendOneMessage(String toName, String message) throws IOException {
        Session session = sessionMap.get(toName);

        if (sessionMap.containsKey(toName)) {
            session.getBasicRemote().sendText(message);
        }
    }
}
