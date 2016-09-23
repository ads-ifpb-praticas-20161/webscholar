package jsfbeans;

import dac.webscholar.sessionbeans.course.CourseProxyQualifier;
import dac.webscholar.sessionbeans.course.CourseServiceLocal;
import dac.webscholar.shared.entities.Course;
import dac.webscholar.shared.exceptions.ValidationException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
/**
 * Created by marcusviniv on 23/09/2016.
 */

@Named
@RequestScoped
public class CoursesMB {

    private Course newCourse;

    @Inject
    @CourseProxyQualifier
    private CourseServiceLocal courseService;

    @Inject
    private FacesMessagesFacade fmf;

    @PostConstruct
    private void init(){
        newCourse = new Course();
    }

    public void salvar(){
        try {
            courseService.saveCourse(newCourse);
            fmf.successMsg("sucesso ao cadastrar " + newCourse.getName(), null);
        }
        catch(ValidationException e){
            fmf.errorMsg(e.getMessage(), null);
        }

    }

    public Course getNewCourse(){
        return newCourse;
    }

    public void setNewCourse(Course newCourse) {
        this.newCourse = newCourse;
    }


}
