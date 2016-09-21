package dac.webscholar.tests;

import dac.webscholar.Utils.PatternValidator;
import dac.webscholar.Utils.ValidatorType;
import dac.webscholar.cdiqualifiers.LoginProxy;
import dac.webscholar.cdiqualifiers.LoginService;
import dac.webscholar.cdiqualifiers.PatternValidatorQualifier;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

/**
 * Created by marcusviniv on 21/09/2016.
 */

@RunWith(Arquillian.class)
public class UnitTest extends ArquillianTest {

    @Inject
    @PatternValidatorQualifier(type = ValidatorType.EMAIL)
    private PatternValidator emailValidator;


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
