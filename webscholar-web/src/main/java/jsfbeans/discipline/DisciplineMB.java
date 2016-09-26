package jsfbeans.discipline;

import dac.webscholar.sessionbeans.discipline.DisciplineProxyQualifier;
import dac.webscholar.sessionbeans.discipline.DisciplineServiceLocal;
import dac.webscholar.shared.entities.Discipline;
import dac.webscholar.shared.exceptions.ValidationException;
import jsfbeans.CourseListMB;
import jsfbeans.FacesMessagesFacade;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by marcusviniv on 26/09/2016.
 */

@Named
@ViewScoped
public class DisciplineMB implements Serializable {


    @Inject
    private CourseListMB courseListMB;

    @Inject
    private FacesMessagesFacade fmf;

    @Inject
    @DisciplineProxyQualifier
    private DisciplineServiceLocal disciplineService;

    private Discipline discipline;

    private List<Discipline> listaDisciplinas;

    private String searchDiscName;

    private Discipline selectedDiscipline;

    private Discipline beforeUpdate;

    @PostConstruct
    private void init(){
        discipline = new Discipline();
        listaDisciplinas = disciplineService.listAll();
    }


    public void saveDiscipline(){
        discipline.setCourse( courseListMB.getSelectedCourse() );

        try {
            disciplineService.save(discipline);
            fmf.successMsg("sucesso ao salvar disciplina", null);
        }
        catch(ValidationException e){
            fmf.errorMsg(e.getMessage(), null);
        }
    }

    public void searchByName(){
        try {
            listaDisciplinas = disciplineService.findByName(searchDiscName);

        }
        catch(ValidationException e){
            fmf.errorMsg(e.getMessage(), null);
        }
    }


    private void backupDiscipline(Discipline source, Discipline target){
        target.setCourse(source.getCourse());
        target.setId(source.getId());
        target.setSeason(source.getSeason());
        target.setName(source.getName());
    }

    public void select(Discipline disc){
        beforeUpdate = new Discipline();
        backupDiscipline(disc, beforeUpdate);

        selectedDiscipline = disc;

    }

    public void updateDiscipline(){
        try {
            System.out.println("ATUALIZAR DISCIPLINA!");

            selectedDiscipline.setCourse(courseListMB.getSelectedCourse());
            System.out.println("TENTAR ATUALIZAR DISCIPLINA");
            disciplineService.update(selectedDiscipline);
            System.out.println("ATUALIZOU DISCIPLINA");
            fmf.successMsg("sucesso ao atualizar disciplina", null);
        }
        catch(ValidationException e){
            backupDiscipline(beforeUpdate, selectedDiscipline);
            fmf.errorMsg(e.getMessage(), null);
        }
    }


    public void removeDiscipline(){
        try{
            disciplineService.remove(selectedDiscipline);
            fmf.successMsg("sucesso ao remover disciplina", null);
        }
        catch(ValidationException e){
            fmf.errorMsg(e.getMessage(), null);
        }
    }



    public CourseListMB getCourseListMB() {
        return courseListMB;
    }

    public void setCourseListMB(CourseListMB courseListMB) {
        this.courseListMB = courseListMB;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public List<Discipline> getListaDisciplinas() {
        return listaDisciplinas;
    }

    public void setListaDisciplinas(List<Discipline> listaDisciplinas) {
        this.listaDisciplinas = listaDisciplinas;
    }

    public String getSearchDiscName() {
        return searchDiscName;
    }

    public void setSearchDiscName(String searchDiscName) {
        this.searchDiscName = searchDiscName;
    }

    public Discipline getSelectedDiscipline() {
        return selectedDiscipline;
    }

    public void setSelectedDiscipline(Discipline selectedDiscipline) {
        this.selectedDiscipline = selectedDiscipline;
    }
}
