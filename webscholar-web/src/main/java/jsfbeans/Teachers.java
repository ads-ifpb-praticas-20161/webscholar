/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfbeans;

import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.Teacher;
import dac.webscholar.shared.interfaces.TeacherService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author vmvini
 */

@Named
@ViewScoped
public class Teachers {
    
    
    @Inject
    private ListStrategyBuilder<Teacher> listStrategyBuilder;
    
    private ListStrategy<Teacher> listStrategy;
    
    private List<Teacher> teachers;
    
    @EJB
    private MailSender mailSender;
    
    @EJB
    private TeacherService userService;
    
    public void allow(Teacher teacher){
        teacher.setActivated(true);
        userService.update(teacher);
        mailSender.sendMail(teacher.getEmail(), "Cadastro no WebScholar Aprovado!", "PARABÉNS, AGORA VOCÊ TRABALHA NO WEBSHCOLAR!");
        
    }
    
    public void denie(Teacher teacher){
        userService.delete(teacher.getId());
    }
    
    @PostConstruct
    public void init(){
        listStrategy = listStrategyBuilder.createListStrategy()
                                            .<Boolean>addParameter("activated", false)
                                            .getListStrategy();
        
        teachers = listStrategy.getResultList();
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    
}
