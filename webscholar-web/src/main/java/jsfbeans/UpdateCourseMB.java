package jsfbeans;

import dac.webscholar.shared.entities.Course;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by marcusviniv on 24/09/2016.
 */

@Named
@RequestScoped
public class UpdateCourseMB {

    private Course selectedCourse;

    @PostConstruct
    private void init(){
        System.out.println("INSTANCIANDO UPDATE COURSE MB");
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest servletRequest = (HttpServletRequest) externalContext.getRequest();
        System.out.println("VAI RECUPERAR SELECTED OBJECT");
        selectedCourse = (Course) servletRequest.getAttribute("selectedObject");
        System.out.println("RECUPEROU " + selectedCourse.getName());

    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public void setSelectedCourse(Course selectedCourse) {
        this.selectedCourse = selectedCourse;
    }
}
