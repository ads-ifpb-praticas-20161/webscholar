/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.sessionbeans;

import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.interfaces.UserService;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author vmvini
 */


@Stateless
@Remote(UserService.class)
public class UserServiceImpl extends GenericCrudService<ScholarUser> implements UserService {

    @Override
    protected void validate(ScholarUser t) {
        
    }
    
    
    
}
