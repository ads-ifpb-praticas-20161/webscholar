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

    private Date createTime(int hour, int minute){
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DATE, 0);
        date.set(Calendar.HOUR, hour);
        date.set(Calendar.MINUTE, minute);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(Calendar.MONTH, 0);
        date.set(Calendar.YEAR, 1970);

        //date.setTimeZone(TimeZone.getDefault());

        return date.getTime();
    }

    public void listIntervals(){
        TypedQuery<IntervalUnit> ius = em.createQuery("SELECT i FROM IntervalUnit i", IntervalUnit.class);
        List<IntervalUnit> list = ius.getResultList();
        Iterator<IntervalUnit> it = list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }


    public void verHora(){
        Date h = createTime(19,0);
        System.out.println(h);
    }

    //@Test(expected = RollbackException.class)
    public void salvarMesmaAulaSalaDiscProfHorario(){
        /*System.out.println("salvar aula");

        IntervalUnitPK interval = new IntervalUnitPK(createTime(19, 0), createTime(19,50));

        RoomSchedulingPK rspk = new RoomSchedulingPK(DayEnum.SEGUNDA, interval, 1);
        RoomScheduling roomScheduling = em.find(RoomScheduling.class, rspk);

        Teacher teacher = em.find(Teacher.class, 1);
        Discipline dis = em.find(Discipline.class, new DisciplinePK("DAC", 1));*/


    }

    @Test
    public void salvarAulaEmHorarioESalaOcupado(){
       System.out.println("ola");
    }

    @After
    public void end() {
        this.em.close();
    }

}
