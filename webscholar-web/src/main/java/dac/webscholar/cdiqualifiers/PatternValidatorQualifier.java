package dac.webscholar.cdiqualifiers;

import dac.webscholar.Utils.ValidatorType;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by marcusviniv on 21/09/2016.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface PatternValidatorQualifier {
    ValidatorType type() default ValidatorType.EMAIL;
}
