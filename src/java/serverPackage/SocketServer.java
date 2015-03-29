/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverPackage;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author c0647456
 */
@ServerEndpoint("/chat")
public class SocketServer {

    @OnMessage
    public String onMessage(String message, Session s) {
        System.out.println("Message from: "+s.getId() +", Message: "+message);
        sendMsg(message, s);
        return null;
    }

    @OnOpen
    public void onOpen(Session s) {
        System.out.println("Connection opened "+s.getId());
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Some Error Occured");
    }

    @OnClose
    public void onClose(Session s) {
        System.out.println("Connection Closed By "+s.getId());
    }
    
    private void sendMsg(String msg, Session s){
        System.out.println("Sending Msg: "+msg);
        s.getAsyncRemote().sendText(msg);
    }
    
}
