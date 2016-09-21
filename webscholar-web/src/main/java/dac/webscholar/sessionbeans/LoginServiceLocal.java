package dac.webscholar.sessionbeans;

import dac.webscholar.shared.entities.ScholarUser;

import javax.security.auth.login.LoginException;

/**
 * Created by marcusviniv on 21/09/2016.
 */
public interface LoginServiceLocal {

    ScholarUser login(String email, String password) throws LoginException;

}
