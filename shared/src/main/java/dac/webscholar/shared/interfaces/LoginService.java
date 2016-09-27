package dac.webscholar.shared.interfaces;

import dac.webscholar.shared.entities.ScholarUser;

import javax.persistence.NoResultException;
import javax.security.auth.login.LoginException;

/**
 * Created by marcusviniv on 21/09/2016.
 */
public interface LoginService {

    ScholarUser login(String email, String password) throws LoginException;

}
