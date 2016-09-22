/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.clientdesktop;

import dac.webscholar.shared.interfaces.SomeService;

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

    
    public static void main(String[] args){
        
        EJBLocator locator = buildServiceLocator("0.0.0.0", "370");
        SomeService service = locator.lookup("java:global/webscholar-web/SomeServiceBean!dac.webscholar.shared.interfaces.SomeService", SomeService.class);
        
        System.out.println(service.hello());
    
    }
    
}
