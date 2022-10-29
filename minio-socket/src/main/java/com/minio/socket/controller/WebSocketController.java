package com.minio.socket.controller;

import cn.hutool.log.Log;
import com.minio.common.exception.ServiceException;
import com.minio.common.utils.uuid.IdUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.slf4j.LoggerFactory;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/socket")
@Controller
public class WebSocketController {

    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);
    private static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    private String authentication;
    private volatile static String historyMessage = "";

    @OnOpen
    public void onOpen(Session session,  EndpointConfig config){
        this.authentication = IdUtils.fastSimpleUUID();
        log.info("用户连接:"+authentication);
        if(!sessionMap.containsKey(authentication)){
            sessionMap.put(authentication, session);
        }
        sendOneMessage(authentication, historyMessage);
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        log.info(authentication + "消息: {}", message);
        historyMessage = message;
        sendAllMessage(historyMessage);
    }

    @OnClose
    public void onClose() {
        sessionMap.remove(authentication);
        log.info("用户:"+authentication+"断开连接");

    }

    @OnError
    public void onError(Throwable throwable) {
        sessionMap.remove(authentication);
        log.info("用户:"+authentication+"断开连接");
        log.info("发生错误");
    }

    public void sendAllMessage(String message) throws IOException {
//        for (Object key : sessionMap.keySet()) {
//            sessionMap.get(key).getBasicRemote().sendText(message);
//        }


        sessionMap.forEach((key, session) -> {
            if (!key.equals(authentication)) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    throw new ServiceException("发送消息失败");
                }
            }
        });

    }

    public void sendOneMessage(String toAuthentication, String message)  {
        Session session = sessionMap.get(toAuthentication);

        if (sessionMap.containsKey(toAuthentication)) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                throw new ServiceException("发送消息失败");
            }
        }
    }
}
