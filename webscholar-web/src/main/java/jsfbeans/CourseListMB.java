package jsfbeans;

import dac.webscholar.sessionbeans.course.CourseProxyQualifier;
import dac.webscholar.sessionbeans.course.CourseServiceLocal;
import dac.webscholar.shared.entities.Course;
import dac.webscholar.shared.exceptions.ValidationException;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by marcusviniv on 24/09/2016.
 */

@Named
@ViewScoped
public class CourseListMB implements Serializable{

    private String courseName;
    private Course selectedCourse;
    private List<Course> resultList;


    @Inject
    private FacesMessagesFacade fmf;

    @Inject
    @CourseProxyQualifier
    private CourseServiceLocal courseService;

    @PostConstruct
    private void init(){
        resultList = courseService.listAll();
    }

    public void searchCourse(){
        try {
            resultList = courseService.searchByName(courseName);
        }
        catch(ValidationException e){
            fmf.errorMsg(e.getMessage(), null);
        }
    }

    public void select(Course selected){
        System.out.println("selecionou o " + selected);
        selectedCourse = selected;
    }

    public void updateCourse(){
        try {
            courseService.updateCourse(selectedCourse);
        }
        catch(ValidationException e){
            fmf.errorMsg(e.getMessage(), null);
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
    }

    public List<Course> getResultList() {
        return resultList;
    }

    public void setResultList(List<Course> resultList) {
        this.resultList = resultList;
    }
}
