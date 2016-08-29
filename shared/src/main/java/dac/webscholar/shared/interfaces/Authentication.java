/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.interfaces;

import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.entities.UserType;

/**
 *
 * @author vmvini
 */
public interface Authentication {
    
    ScholarUser login(String email, String password, UserType userType);
    
}
