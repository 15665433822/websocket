package com.abin.websocket.springbootwebsocket.websocket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndpoint {

    @OnOpen
    public void openSession(@PathParam("username")String username, Session session){
        sendText(session,username);
    }

    @OnMessage
    public void OnMessage(@PathParam("username")String username, Session session,String message){
        sendText(session,"用户["+username+"]:"+message);
    }

    @OnClose
    public void OnClose(@PathParam("username")String username, Session session,String message){
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendText(Session session,String message){
        RemoteEndpoint.Basic basic = session.getBasicRemote();
        try {
            basic.sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
