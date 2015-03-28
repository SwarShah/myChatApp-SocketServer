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
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author c0647456
 */
@ServerEndpoint("/chat")
public class SocketServer {

    @OnMessage
    public String onMessage(String message) {
        System.out.println(message);
        return null;
    }

    @OnOpen
    public void onOpen() {
        System.out.println("Connection opened");
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Some Error Occured");
    }

    @OnClose
    public void onClose() {
        System.out.println("Connection Closed By Client");
    }
    
}
