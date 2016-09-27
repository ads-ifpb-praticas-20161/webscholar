package dac.webscholar.shared.interfaces;

import dac.webscholar.shared.entities.Lecture;
import dac.webscholar.shared.exceptions.ValidationException;

import java.util.List;

/**
 * Created by marcusviniv on 26/09/2016.
 */
public interface LectureService {

    Lecture save(Lecture lecture) throws ValidationException;

    Lecture update(Lecture lecture)  throws ValidationException;

    void remove(Lecture lecture) throws ValidationException;

    List<Lecture> listAll();

}
