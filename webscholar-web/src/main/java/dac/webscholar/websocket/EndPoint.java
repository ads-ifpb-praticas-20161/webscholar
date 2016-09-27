package dac.webscholar.websocket;

import dac.webscholar.sessionbeans.teacher.TeacherRequestMessage;
import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.entities.UserType;

import javax.annotation.Resource;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by marcusviniv on 25/09/2016.
 */

@Named
@ServerEndpoint(value = "/websocket", configurator = WebSocketLoggedUserConfig.class)
public class EndPoint {

    @Inject
    private WebSocketClients sessions;
/*
    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private ConnectionFactory cf;

    @Resource(lookup = "jms/myQueue")
    private Queue queue;
*/
    @OnOpen
    public void onOpen(final Session session, EndpointConfig config){

        ScholarUser user = (ScholarUser) config.getUserProperties().get("user");
        if(user != null){
            System.out.println("um cliente conectou: " + user.getName());
        }
        else{
            System.out.println("cliente nao logado conectou");
        }
        sessions.add(session, user);
    }

    public void onJMSMessage(@Observes @TeacherRequestMessage Message msg) {
        try {
            String message = msg.getBody(String.class);

            Map<ScholarUser, Set<Session>> userSessions = sessions.getUserSessions(UserType.ADMIN);
            userSessions.forEach((k, v) -> {
                v.forEach(sv -> {
                    try {
                        sv.getBasicRemote().sendText(message);
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                });
            });
        }
        catch(JMSException e){
            e.printStackTrace();
        }

    }


    @OnMessage
    public void onMessage(final String message, final Session client){
        //message handler
    }

    @OnClose
    public void onClose(final Session session){
        sessions.remove(session);
    }

}
