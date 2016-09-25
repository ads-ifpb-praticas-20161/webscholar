package dac.webscholar.sessionbeans.teacher;

import dac.webscholar.shared.entities.Teacher;
import dac.webscholar.shared.exceptions.ValidationException;

import java.util.List;

/**
 * Created by marcusviniv on 25/09/2016.
 */
public interface TeacherServiceLocal {

    Teacher saveTeacher(Teacher teacher) throws ValidationException;

    Teacher updateTeacher(Teacher teacher) throws ValidationException;

    void removeTeacher(Teacher teacher) throws ValidationException;

    List<Teacher> listAll() throws ValidationException;

    Teacher searchByCpf(String cpf) throws ValidationException;

    List<Teacher> searchByName(String name) throws ValidationException;

}
