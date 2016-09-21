package dac.webscholar.sessionbeans;

import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.ScholarUser;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * Created by marcusviniv on 21/09/2016.
 */

@dac.webscholar.cdiqualifiers.LoginService
public class Login implements LoginService{

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
