package dac.webscholar.Utils;


import dac.webscholar.cdiqualifiers.PatternValidatorQualifier;

/**
 * Created by marcusviniv on 21/09/2016.
 */

@PatternValidatorQualifier(type = ValidatorType.CPF)
public class CpfValidator extends GenericPatternValidator {

    protected String getStrPattern(){
        return"([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})";
    }

}
