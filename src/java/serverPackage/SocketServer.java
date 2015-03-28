/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverPackage;

import javax.websocket.OnMessage;
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
    
}
