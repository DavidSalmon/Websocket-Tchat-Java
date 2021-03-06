package websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

@ServerEndpoint(value = "/testSocket")
public class ChatEndPoint {

	private Map<String,ChatUser> mapChatUsers;
	private static CopyOnWriteArrayList<Session> userSessions;
	
	@OnOpen
	public void onOpen(Session session){
		System.out.println("nouvelle connection sur la websocket");
		if(this.userSessions == null){
			userSessions = new CopyOnWriteArrayList<Session>();
			
		}
		userSessions.add(session);
		System.out.println("Personne connect� :" + userSessions.size());
	}
	
	@OnMessage
	public void handleMessage(String message,Session s) throws IOException{
		if(!s.getUserProperties().containsKey("pseudo")){
			s.getUserProperties().put("pseudo", message);
			for(Session session:userSessions){
				if(s.equals(session)){
					session.getBasicRemote().sendText("Vous �tes maintenant connect� en tant que " +message+".");
				}else{
					session.getBasicRemote().sendText(message +" a rejoint le tchat.");
				}
			}
		}else{
			for(Session session:userSessions){
				session.getBasicRemote().sendText(s.getUserProperties().get("pseudo") +" : "+message);
			}
		}
		//System.out.println("message : " + message);
		
	}
	
	@OnClose
	public void removeSession(Session s){
		String pseudo = (String)s.getUserProperties().get("pseudo");
		userSessions.remove(s);
		for(Session session : userSessions){
				try {
					session.getBasicRemote().sendText(pseudo+" a quitt� le tchat");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			
		}
		
	}
}
