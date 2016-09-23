/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.clientdesktop;

import dac.webscholar.shared.interfaces.LoginService;
import dac.webscholar.shared.interfaces.SomeService;
import org.eclipse.persistence.sessions.Login;

import javax.security.auth.login.LoginException;

/**
 *
 * @author vmvini
 */
public class Main {
    
    private static EJBLocator buildServiceLocator(String host, String port){
        EJBLocator sl = new EJBLocator();
        sl.setHost(host);
        sl.setPort(port);
        return sl;
    }

    
    public static void main(String[] args) throws LoginException{
        
        EJBLocator locator = buildServiceLocator("192.168.99.100", "3700");
        //SomeService service = locator.lookup("java:global/webscholar-web/SomeServiceBean!dac.webscholar.shared.interfaces.SomeService", SomeService.class);

        LoginService loginService = locator.lookup("java:global/webscholar-web/LoginProxy!dac.webscholar.shared.interfaces.LoginService", LoginService.class);

        //System.out.println(service.hello());

        loginService.login("admin@gmail.com", "admin");

    }
    
}
