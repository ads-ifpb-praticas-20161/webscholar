/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.sessionbeans;

import dac.webscholar.shared.entities.Admin;
import dac.webscholar.shared.interfaces.AdminService;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author vmvini
 */
@Stateless(mappedName = "AdminService")
@Remote(AdminService.class)
public class AdminServiceImpl extends GenericCrudService<Admin> implements AdminService {

    @Override
    protected void validate(Admin t) {
        
    }
    
}
