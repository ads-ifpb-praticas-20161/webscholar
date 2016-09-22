package dac.webscholar.desktop.tests;

import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.interfaces.LoginService;
import ifpb.locator.ConfigContext;
import ifpb.locator.ServerContext;
import ifpb.locator.ejb.GlassFishContext;
import ifpb.locator.named.App;
import ifpb.locator.named.BeanLocator;
import ifpb.locator.named.Scoped;
import org.junit.Test;

import javax.security.auth.login.LoginException;

/**
 * Created by marcusviniv on 21/09/2016.
 */
public class LookupTest {


    public void testSum(){
        assert 2 == 2;
    }


    public void testLookup(){
        System.out.println("executando");
        ConfigContext glassFishContext = new GlassFishContext("192.168.99.100", "7300");
        App module = App.name("webscholar-web").namespace(Scoped.GLOBAL);
        ServerContext serverContext = new ServerContext(glassFishContext, module);

        BeanLocator bl = serverContext.bean("LoginProxy");
        LoginService loginService = bl.locate();

        //LoginService loginService = serverContext.lookup("java:global/webscholar-web/LoginProxy!dac.webscholar.shared.interfaces.LoginService", LoginService.class);

        try {
            ScholarUser user = loginService.login("admin@gmail.com", "admin");
            System.out.println("ola " + user.getName());
        }
        catch(LoginException e){
            System.out.println(e);
        }

    }

}
