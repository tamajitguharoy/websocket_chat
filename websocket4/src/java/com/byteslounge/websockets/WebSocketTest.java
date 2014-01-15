package com.byteslounge.websockets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/websocket", configurator = CustomConfigurator.class)
public class WebSocketTest {
 private static Map<String,Session> clients =  Collections.synchronizedMap (new HashMap<String,Session>());    
 private HttpSession httpSession;

    public void setHttpSession(HttpSession httpSession)
    {
        if (this.httpSession != null)
        throw new IllegalStateException("HttpSession has already been set!");
    this.httpSession = httpSession;
    }
	
	@OnMessage
    public void onMessage(String message, Session session) 
    	throws IOException {
     // Map<String, List<String>> requestParameterMap = session.getRequestParameterMap();
     //    List<String> toUsers = requestParameterMap.get("touser");
     String[] messageparts = message.split(";;;");
		synchronized(clients){
         
         
         String msg=(String)httpSession.getAttribute("fromuser")+":  "+messageparts[1];
         
         Session sendSession = clients.get(messageparts[0]);
         if(sendSession!=null){
                sendSession.getBasicRemote().sendText(msg);  
                
                  
         }
         
         
         Session sendSession2 = clients.get((String)httpSession.getAttribute("fromuser"));
         if(sendSession2!=null){
                sendSession2.getBasicRemote().sendText(msg);  
                
                  
         }
                    
		}
		
    }
	
	@OnOpen
    public void onOpen (Session session, EndpointConfig config) {
        httpSession = (HttpSession) config.getUserProperties()
                                           .get(HttpSession.class.getName());
		// Add session to the connected sessions set
System.out.println("88888888888888888888888888888888888888888888888888888888888"+httpSession);
       System.out.println(httpSession.getAttribute("fromuser")+"  from WebSocketTest.java=================================================");
		clients.put((String)httpSession.getAttribute("fromuser"),session);
    }

    @OnClose
    public void onClose (Session session) {
    	// Remove session from the connected sessions set
    	clients.remove((String)httpSession.getAttribute("fromuser"));
    }
}
