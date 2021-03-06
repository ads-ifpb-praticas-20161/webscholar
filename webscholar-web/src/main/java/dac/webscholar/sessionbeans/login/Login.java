package dac.webscholar.sessionbeans.login;

import dac.webscholar.cdiqualifiers.LoginServiceQualifier;
import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.interfaces.LoginService;

import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by marcusviniv on 21/09/2016.
 */

@LoginServiceQualifier
public class Login implements Serializable,  LoginService {

    @Inject
    private ListStrategyBuilder<ScholarUser> listStrategyBuilder;

    private ListStrategy<ScholarUser> listStrategy;

    public ScholarUser login(String email, String password){

        listStrategy = listStrategyBuilder
                        .createListStrategy()
                        .<String>addParameter("email", email)
                        .<String>addParameter("password", password)
                        .getListStrategy();

        ScholarUser user = listStrategy.getSingleResult();
        return user;

    }

}
