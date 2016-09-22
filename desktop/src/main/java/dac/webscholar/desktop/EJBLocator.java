package dac.webscholar.desktop;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by marcusviniv on 22/09/2016.
 */
public class EJBLocator {

    private String host = "pcCore";
    private String port = "3700";

    public void setHost(String host){
        this.host = host;
    }

    public void setPort(String port){
        this.port = port;
    }

    public <T> T lookup(String recurso, Class<T> tipo) {
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("org.omg.CORBA.ORBInitialHost", host);
            props.setProperty("org.omg.CORBA.ORBInitialPort", port);
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty(Context.STATE_FACTORIES, "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            props.setProperty(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");

            InitialContext context = new InitialContext(props);
            System.out.println("gerou contexto");
            return (T) context.lookup(recurso);
        } catch (Exception ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            ne.printStackTrace();
            throw new RuntimeException(ne);
        }
    }

}
