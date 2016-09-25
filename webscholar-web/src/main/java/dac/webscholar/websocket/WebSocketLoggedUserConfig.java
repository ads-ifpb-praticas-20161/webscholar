package dac.webscholar.websocket;

import dac.webscholar.shared.entities.ScholarUser;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * Created by marcusviniv on 25/09/2016.
 */

public class WebSocketLoggedUserConfig extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        ScholarUser user = (ScholarUser) httpSession.getAttribute("user");
        config.getUserProperties().put("user", user);
    }

}
