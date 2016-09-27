package dac.webscholar.sessionbeans.lecture;

import dac.webscholar.shared.entities.Lecture;
import dac.webscholar.shared.exceptions.ExceptionTypes;
import dac.webscholar.shared.exceptions.ValidationException;
import dac.webscholar.shared.interfaces.LectureService;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by marcusviniv on 26/09/2016.
 */
@Stateless
@LectureProxyQualifier
@Remote(LectureService.class)
@Local(LectureServiceLocal.class)
public class LectureProxy  implements Serializable, LectureService, LectureServiceLocal {

    @Inject
    @LectureServiceQualifier
    private LectureService lectureService;

    private void validate(Lecture lecture) throws ValidationException{
        if(lecture == null){
            throw new ValidationException("Aula está nula!");
        }

        if(lecture.getDiscipline() == null){
            throw new ValidationException("Disciplina está nula!");
        }

        if(lecture.getRoomScheduling() == null){
            throw new ValidationException("A alocação de sala não foi feita");
        }

        if(lecture.getTeacherScheduling() == null){
            throw new ValidationException("A alocação do professor não foi feita");
        }

    }


    @Override
    public Lecture save(Lecture lecture) throws ValidationException {
        validate(lecture);

        lecture.getTeacherScheduling().setDiscipline(

                lecture.getDiscipline()

        );

        lecture.getTeacherScheduling().setCourse(

                lecture.getDiscipline().getCourse()

        );


        try {
            return lectureService.save(lecture);
        }
        catch(ValidationException e){
            if (e.getExceptionType().equals(ExceptionTypes.DATABASE)) {
                throw new ValidationException("Aula em conflito com outras",  e.getCause(), e.getExceptionType());
            }
            return null;
        }
    }

    @Override
    public Lecture update(Lecture lecture) throws ValidationException {
        validate(lecture);
        try {
            return lectureService.save(lecture);
        }
        catch(ValidationException e){
            if (e.getExceptionType().equals(ExceptionTypes.DATABASE)) {
                throw new ValidationException("Aula em conflito com outras",  e.getCause(), e.getExceptionType());
            }
            return null;
        }
    }

    @Override
    public void remove(Lecture lecture) throws ValidationException {

        validate(lecture);
        try {
            lectureService.remove(lecture);
        }
        catch(ValidationException e){
            if (e.getExceptionType().equals(ExceptionTypes.DATABASE)) {
                throw new ValidationException("Outros dados dependenm dessa aula",  e.getCause(), e.getExceptionType());
            }

        }

    }

    @Override
    public List<Lecture> listAll() {
        return lectureService.listAll();
    }
}
