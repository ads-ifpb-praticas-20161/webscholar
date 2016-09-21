package dac.webscholar.sessionbeans;

import dac.webscholar.shared.entities.ScholarUser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by marcusviniv on 21/09/2016.
 */

@Stateless
@dac.webscholar.cdiqualifiers.LoginProxy
public class LoginProxy implements LoginService {

    @Inject
    @dac.webscholar.cdiqualifiers.LoginService
    private Login loginService;

    private final String emailPattern = "^[_A-Za-z0-9-]+(\\." +
            "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
            "(\\.[A-Za-z]{2,})$";
    private Pattern pattern;
    private Matcher matcher;


    @Override
    public ScholarUser login(String email, String password) throws LoginException {

        if(email == null || email.trim().isEmpty() ){
            throw new LoginException("Digite seu email!");
        }
        if(isEmailInvalid(email)){
            throw new LoginException("Email inválido!");
        }
        if(password == null || password.trim().isEmpty()){
            throw new LoginException("Digite sua senha!");
        }
        try{
            ScholarUser user = loginService.login(email, password);
            return user;
        }
        catch(RuntimeException e){
            throw new LoginException("email ou senha incorretos");
        }
    }

    private boolean isEmailInvalid(String email){
        pattern = Pattern.compile(emailPattern);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
