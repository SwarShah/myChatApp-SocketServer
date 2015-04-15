/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverPackage;

import com.google.common.collect.Maps;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
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

    private static final Set<Session> liveSessions = Collections.synchronizedSet(new HashSet<Session>());
    private static final HashMap<String, String> nameWithSession = new HashMap<>();
    private JSONHelper json = new JSONHelper();

    @OnMessage
    public void onMessage(String message, Session s) {
        System.out.println("Message from: " + s.getId() + ", Message: " + message);
        //Reading text json and adding in JsonObject
        JsonReader jsonReader = Json.createReader(new StringReader(message));
        JsonObject jsonRead = jsonReader.readObject();
        jsonReader.close();

        //Getting value of flag from JsonObject
        String msg = jsonRead.getString("message");
        sendMessageToAll(s.getId(), nameWithSession.get(s.getId()),
                msg, false, false);

    }

    @OnOpen
    public void onOpen(Session s) {
        System.out.println("Connection opened " + s.getId());
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("Some Error Occured");
    }

    @OnClose
    public void onClose(Session s) {
        System.out.println("Connection Closed By " + s.getId());
        String name = nameWithSession.get(s.getId());
        liveSessions.remove(s);
        sendMessageToAll(s.getId(), name, " left conversation!", false,
                true);
    }

    private void sendMsg(String msg, Session s) {
        System.out.println("Sending Msg: " + msg);
        s.getAsyncRemote().sendText(msg);
    }

    private void sendMessageToAll(String sessionId, String name,
            String message, boolean isNewClient, boolean isExit) {
        for (Session s : liveSessions) {
            String jsonString = "";
            if (isNewClient) {
                jsonString = json.getConnectJson(sessionId, name, message,
                        liveSessions.size());

            } else if (isExit) {
                jsonString = json.getDisconnectJson(sessionId, name, message,
                        liveSessions.size());
            } else {
                jsonString = json
                        .getMessageJson(sessionId, name, message);
            }
            try {
                s.getBasicRemote().sendText(jsonString);
            } catch (IOException e) {
                System.out.println("Error");
                e.printStackTrace();
            }
        }
    }

    // Getting query parameters
    private static Map<String, String> getQueryMap(String query) {
        Map<String, String> map = Maps.newHashMap();
        if (query != null) {
            String[] params = query.split("&");
            for (String param : params) {
                String[] nameval = param.split("=");
                map.put(nameval[0], nameval[1]);
            }
        }
        return map;
    }

}
