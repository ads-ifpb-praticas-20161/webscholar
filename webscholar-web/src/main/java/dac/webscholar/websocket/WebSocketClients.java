package dac.webscholar.websocket;

import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.entities.UserType;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by marcusviniv on 25/09/2016.
 */

@ApplicationScoped
public class WebSocketClients {

    private Map<ScholarUser, Set<Session>> sessions;

    @PostConstruct
    public void init(){
        sessions = new ConcurrentHashMap<>();
    }

    public void add(Session session, ScholarUser user){
        sessions.computeIfAbsent(user, v -> ConcurrentHashMap.newKeySet()).add(session);
    }

    public void remove(Session session){
        sessions.values().forEach(v -> v.removeIf(e -> e.equals(session)));
    }

    public Map<ScholarUser, Set<Session>> getUserSessions(UserType userType){
        Map<ScholarUser, Set<Session>> result = sessions
                .entrySet()
                .stream()
                .filter(u -> u.getKey().getUserType().equals(userType))
                .collect(Collectors.toMap(u -> u.getKey(), u -> u.getValue()));

        return result;

    }

    public Map<ScholarUser, Set<Session>> getUserSessions(){
        return sessions;
    }

}
