package dac.webscholar.sessionbeans;

import dac.webscholar.shared.interfaces.SomeService;
import java.io.Serializable;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Created by marcusviniv on 22/09/2016.
 */

@Stateless
@Remote(SomeService.class)
public class SomeServiceBean implements Serializable, SomeService{

    public String hello(){
        return "hi";
    }

}
