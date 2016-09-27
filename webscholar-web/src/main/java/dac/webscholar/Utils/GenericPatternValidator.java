package dac.webscholar.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by marcusviniv on 21/09/2016.
 */
public abstract class GenericPatternValidator implements PatternValidator {

    private Pattern pattern;
    private Matcher matcher;

    public GenericPatternValidator(){
        pattern = Pattern.compile(getStrPattern());
    }

    protected abstract String getStrPattern();

    public boolean isValid(String str){
        matcher = pattern.matcher(str);
        return matcher.matches();
    }

}
