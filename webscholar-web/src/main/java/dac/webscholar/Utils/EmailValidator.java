package dac.webscholar.Utils;

import dac.webscholar.cdiqualifiers.PatternValidatorQualifier;

/**
 * Created by marcusviniv on 21/09/2016.
 */

@PatternValidatorQualifier(type = ValidatorType.EMAIL)
public class EmailValidator extends GenericPatternValidator {

    protected String getStrPattern(){
        return "^[_A-Za-z0-9-]+(\\." +
                "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
                "(\\.[A-Za-z]{2,})$";
    }
}
