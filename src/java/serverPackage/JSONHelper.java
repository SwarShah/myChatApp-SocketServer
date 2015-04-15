/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverPackage;

import javax.json.Json;

/**
 *
 * @author c0647456
 */
public class JSONHelper {
    public String getNewClientDetailsJson(String sessionId, String message) {
        return Json.createObjectBuilder()
                .add("flag", "self")
                .add("sessionId", sessionId)
                .add("message", message).build().toString();         
    }
    
    public String getConnectJson(String sessionId, String name,
			String message, int onlineCount) {
        return Json.createObjectBuilder()
                .add("flag", "new")
                .add("name", name)
                .add("sessionId", sessionId)
                .add("message", message)
                .add("onlineCount", onlineCount).build().toString();        
    }
    
    public String getDisconnectJson(String sessionId, String name,
			String message, int onlineCount) {
        return Json.createObjectBuilder()
                .add("flag", "exit")
                .add("name", name)
                .add("sessionId", sessionId)
                .add("message", message)
                .add("onlineCount", onlineCount).build().toString();                   
    }
    
    public String getMessageJson(String sessionId, String fromName,
			String message) {
        return Json.createObjectBuilder()
                .add("flag", "exit")
                .add("name", fromName)
                .add("sessionId", sessionId)
                .add("message", message).build().toString();        
    }
}
