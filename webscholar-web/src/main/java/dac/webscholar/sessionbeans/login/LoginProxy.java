package dac.webscholar.sessionbeans.login;


import dac.webscholar.Utils.PatternValidator;
import dac.webscholar.Utils.ValidatorType;
import dac.webscholar.cdiqualifiers.LoginProxyQualifier;
import dac.webscholar.cdiqualifiers.LoginServiceQualifier;
import dac.webscholar.cdiqualifiers.PatternValidatorQualifier;
import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.interfaces.LoginService;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.io.Serializable;

/**
 * Created by marcusviniv on 21/09/2016.
 */

@Stateless
@LoginProxyQualifier
@Local(LoginServiceLocal.class)
@Remote(LoginService.class)
public class LoginProxy implements Serializable, LoginService, LoginServiceLocal {

    @Inject
    @LoginServiceQualifier
    private Login loginService;

    @Inject
    @PatternValidatorQualifier(type = ValidatorType.EMAIL)
    private PatternValidator validator;


    @Override
    public ScholarUser login(String email, String password) throws LoginException {

        if(email == null || email.trim().isEmpty() ){
            throw new LoginException("Digite seu email!");
        }
        if(!validator.isValid(email)){
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

}
