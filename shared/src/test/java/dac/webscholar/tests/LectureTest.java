package dac.webscholar.tests;

import dac.webscholar.shared.entities.*;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.postgresql.util.PSQLException;

import javax.persistence.*;
import java.util.*;

/**
 * Created by vmvini on 10/09/16.
 */
public class LectureTest {

    private static DBUnitHelper dbUnitHelper;
    private static EntityManagerFactory emFactory;

    private EntityManager em;

    @BeforeClass
    public static void initClass(){

        dbUnitHelper = new DBUnitHelper("DbUnitXml");
        emFactory = Persistence.createEntityManagerFactory("dac.webscholar_shared_jar_1.0PU");

    }

    @Before
    public void init(){
        dbUnitHelper.execute(DatabaseOperation.DELETE_ALL, "Test.xml");
        dbUnitHelper.execute(DatabaseOperation.INSERT, "Test.xml");
        em = emFactory.createEntityManager();

    }

    @Test (expected = RollbackException.class)
    public void insertLectureOnOccupiedRoom(){
        Lecture lecture = new Lecture();
        lecture.setDiscipline(findDiscipline("PRATICAS", 1));
        lecture.setRoomScheduling(findRoomScheduling(1, "M1", DayEnum.SEGUNDA));
        lecture.setTeacherScheduling(findTeacherScheduling(1, "M1", DayEnum.SEGUNDA));

        em.getTransaction().begin();
        em.persist(lecture);
        em.getTransaction().commit();
    }

    @Test (expected = RollbackException.class)
    public void insertBusyTeacherOnAnotherLecture(){
        em.getTransaction().begin();

        Lecture lecture = new Lecture();
        lecture.setDiscipline(findDiscipline("DAC", 1));
        lecture.setRoomScheduling(findRoomScheduling(2, "M1", DayEnum.SEGUNDA));

        TeacherScheduling ts = createTeacherScheduling(1, "M1", DayEnum.SEGUNDA);
        em.persist(ts);

        lecture.setTeacherScheduling(findTeacherScheduling(1, "M1", DayEnum.SEGUNDA));

        em.persist(lecture);

        em.getTransaction().commit();
    }

    @Test( expected = RollbackException.class )
    public void insertLectureFileWithSameNameUnderSameDiscipline(){

        em.getTransaction().begin();

        LectureFile lf = new LectureFile();
        lf.setDiscipline(findDiscipline("DAC", 1));
        lf.setFileName("teste");
        lf.setFilePath("teste.txt");
        lf.setTeacher(findTeacher(1));

        em.persist(lf);

        em.getTransaction().commit();

    }

    @Test
    public void insertLectureFileWithSameNameUnderAnotherDiscipline(){
        em.getTransaction().begin();

        LectureFile lf = new LectureFile();
        lf.setDiscipline(findDiscipline("POS", 1));
        lf.setFileName("teste");
        lf.setFilePath("teste.txt");
        lf.setTeacher(findTeacher(1));

        em.persist(lf);

        em.getTransaction().commit();
    }


    @After
    public void end() {
        this.em.close();
    }


    private Discipline findDiscipline(String disName, int course){
        DisciplinePK pk = new DisciplinePK(disName, course);
        return em.find(Discipline.class, pk);
    }
    private RoomScheduling findRoomScheduling(int room, String intervalUnit, DayEnum weekDay){
        RoomSchedulingPK pk = new RoomSchedulingPK();
        pk.setRoom(room);
        pk.setRoomIntervalUnit(intervalUnit);
        pk.setRoomWeekDay(weekDay);
        return em.find(RoomScheduling.class, pk);

    }
    private TeacherScheduling findTeacherScheduling(int teacher, String intervalUnit,  DayEnum weekDay){
        TeacherSchedulingPK pk = new TeacherSchedulingPK();
        pk.setTeacher(teacher);
        pk.setTeacherIntervalUnit(intervalUnit);
        pk.setTeacherWeekDay(weekDay);
        return em.find(TeacherScheduling.class, pk);
    }

    private Teacher findTeacher(int teacher){
        return em.find(Teacher.class, teacher);
    }

    private IntervalUnit findInterval(String cod){
        return em.find(IntervalUnit.class, cod);
    }

    private TeacherScheduling createTeacherScheduling(int teacher, String intervalUnit, DayEnum weekDay){
        Teacher t = findTeacher(teacher);
        IntervalUnit iu = findInterval(intervalUnit);
        TeacherScheduling ts = new TeacherScheduling(weekDay, iu, t);
        return ts;
    }

}
