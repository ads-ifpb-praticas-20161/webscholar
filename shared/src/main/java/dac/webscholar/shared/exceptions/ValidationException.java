package dac.webscholar.shared.exceptions;

/**
 * Created by marcusviniv on 23/09/2016.
 */
public class ValidationException extends Exception {

    private ExceptionTypes type;

    public ValidationException(String msg){
        super(msg);
        type = ExceptionTypes.VALIDATION;

    }

    public ValidationException(String msg, Throwable cause){
        super(msg, cause);
    }

    public ValidationException(String msg, Throwable cause, ExceptionTypes type){
        super(msg, cause);
        this.type = type;
    }

    public ExceptionTypes getExceptionType(){
        return type;
    }



}
