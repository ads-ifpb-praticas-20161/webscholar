/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.sessionbeans;

import dac.webscholar.shared.entities.Teacher;
import dac.webscholar.shared.interfaces.TeacherService;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author vmvini
 */

@Stateless(mappedName = "TeacherService")
@Remote(TeacherService.class)
public class TeacherServiceImpl extends GenericCrudService<Teacher> implements TeacherService {

    @Override
    protected void validate(Teacher t) {
        
    }
    
}
