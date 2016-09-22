package dac.webscholar.desktop;

import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.interfaces.LoginService;
import dac.webscholar.shared.interfaces.SomeService;
import ifpb.locator.ConfigContext;
import ifpb.locator.ServerContext;
import ifpb.locator.ejb.GlassFishContext;
import ifpb.locator.named.App;
import ifpb.locator.named.BeanLocator;
import ifpb.locator.named.Scoped;

import javax.security.auth.login.LoginException;
import javax.naming.NoInitialContextException;

/**
 * Created by marcusviniv on 21/09/2016.
 */
public class Main {

    private static EJBLocator buildServiceLocator(String host, String port){
        EJBLocator sl = new EJBLocator();
        sl.setHost(host);
        sl.setPort(port);
        return sl;
    }

    public static void main(String[] args) throws LoginException{
        /*
        ConfigContext glassFishContext = new GlassFishContext("192.168.99.100", "7300");
        App module = App.name("webscholar-web").namespace(Scoped.GLOBAL);
        ServerContext serverContext = new ServerContext(glassFishContext, module);


        String jndi = serverContext.bean("LoginProxy").withInterface(LoginService.class).name();

        System.out.println("criou serverContext");
        System.out.println("jndi: " + jndi);


        LoginService loginService = serverContext.lookup(jndi, LoginService.class);

        System.out.println("fez lookup");
*/
        EJBLocator locator = buildServiceLocator("172.20.0.3", "370");
        SomeService loginService = locator.lookup("java:global/core/SomeServiceBean!dac.webscholar.shared.interfaces.SomeService", SomeService.class);


        //ejb:<app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified-classname-of-the-remote-interface>
               // / ScholarUser user = loginService.login("admin@gmail.com", "admin");
            System.out.println(loginService.hello());

            //System.out.println("ola " + user.getName());



        /*
        * System.out.println("testServer");
        context = new GlassFishContext();
        module =  App.name("ejb").namespace(Scoped.GLOBAL);

        server = new ServerContext(context, module);
        assertNotNull("values null in properties", server);

        String beanResult = "java:global/ejb/CalculadoraImpl!ifpb.locator.ServerContext";
        assertEquals(beanResult, server.bean("CalculadoraImpl")
                .withInterface(ServerContext.class)
        *
        * */

    }

}
