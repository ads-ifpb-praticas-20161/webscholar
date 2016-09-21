package dac.webscholar.tests;

import dac.webscholar.Utils.PatternValidator;
import dac.webscholar.Utils.ValidatorType;
import dac.webscholar.cdiqualifiers.LoginProxyQualifier;
import dac.webscholar.cdiqualifiers.LoginServiceQualifier;
import dac.webscholar.cdiqualifiers.PatternValidatorQualifier;
import dac.webscholar.sessionbeans.LoginService;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;

/**
 * Created by marcusviniv on 21/09/2016.
 */

@RunWith(Arquillian.class)
public class UnitTest extends ArquillianTest {

    @Inject
    @PatternValidatorQualifier(type = ValidatorType.EMAIL)
    private PatternValidator emailValidator;

    @Inject
    @LoginProxyQualifier
    private LoginService loginService;


    @Test
    public void doLogin() throws LoginException{
        loginService.login("admin@gmail.com", "admin");
    }

    @Test (expected = LoginException.class)
    public void doLoginWrongUser() throws LoginException{
        loginService.login("admin@gmail.com", "dsadsad");
    }

    @Test (expected = LoginException.class)
    public void doLoginInvalidEmail() throws LoginException{
        loginService.login("dkasjdlakje", "dsadsa");
    }



    @Test
    public void validGmail(){

        Assert.assertEquals(true, emailValidator.isValid("admin@gmail.com"));

    }


    @Test
    public void testInvalidEmail(){
        Assert.assertEquals(false, emailValidator.isValid("DALSKJDLASKJDLKWJE"));
    }

    @Test
    public void validHotmail(){
        Assert.assertEquals(true, emailValidator.isValid("vmvini@hotmail.com"));
    }

}
