package dac.webscholar.sessionbeans.course;

import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.*;
import dac.webscholar.shared.exceptions.ExceptionTypes;
import dac.webscholar.shared.exceptions.ValidationException;
import dac.webscholar.shared.interfaces.CourseService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by marcusviniv on 23/09/2016.
 */

@CourseServiceQualifier
public class CourseServiceImpl implements CourseService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private ListStrategyBuilder<Course> listStrategyBuilder;

    private ListStrategy<Course> listStrategy;

    private CriteriaBuilder cbuilder;


    @Resource
    private SessionContext sessionContext;

    @AroundInvoke
    public Object interceptor(InvocationContext ic) throws Exception {
        Object o = null;
        try {
            o = ic.proceed();
            if (!sessionContext.getRollbackOnly()) {
                em.flush();
            }
        } catch (PersistenceException ex) {
            throw new ValidationException(ex.getMessage(), ex, ExceptionTypes.DATABASE);
        }
        return o;
    }

    @PostConstruct
    private void init(){
        cbuilder = em.getCriteriaBuilder();
    }

    @Override
    public Course saveCourse(Course course)throws ValidationException {
        return em.merge(course);
    }

    @Override
    public Course updateCourse(Course course) throws ValidationException {
        return em.merge(course);

    }

    @Override
    public void removeCourse(Course course)throws ValidationException {
        em.remove(em.find(Course.class, course.getId()));
    }

    @Override
    public List<Course> listAll() {
        listStrategy = listStrategyBuilder.createListStrategy().getListStrategy();
        return listStrategy.getResultList();
    }

    @Override
    public List<Course> listNCourses(int initial, int end) throws ValidationException {
        return null;
    }

    @Override
    public Course searchById(int id) throws ValidationException {
        listStrategy = listStrategyBuilder
                            .createListStrategy()
                            .<Integer>addParameter("id", id)
                            .getListStrategy();

        return listStrategy.getSingleResult();
    }

    @Override
    public List<Course> searchByName(String name) throws ValidationException {
        listStrategy = listStrategyBuilder
                .createListStrategy()
                .<String>addParameter("name", name)
                .getListStrategy();

        return listStrategy.getResultList();
    }


    private List<CursoHorario> listHorario(){

        String sql = "select  c.name course, s.name teacher, d.name discipline, iu.name intervalo, "
                +" ts.teacherweekday dia, iu.initialdate initialdate, iu.enddate enddate "
                +" from intervalunit iu "
                +" left join teacherscheduling ts on ts.teacherintervalunit_name = iu.name "
                +" left join course c on c.id = ts.course_id left join discipline d on d.id = ts.discipline_id "
                +" left join scholaruser s on s.id = ts.teacher_id order by iu.name";

        Query query = em.createNativeQuery(sql, "CursoHorarioMap");
        return query.getResultList();


    }


    private Map<Intervalo, Map<DayEnum, CursoHorario>> createMapTemplate(){
        Map<Intervalo, Map<DayEnum, CursoHorario>> map = new HashMap<>();

        Map<DayEnum, CursoHorario> subMap;

        for(Intervalo t : Intervalo.values()){
            subMap = new HashMap<>();

            for( DayEnum d : DayEnum.values() ){

                subMap.put(d, new CursoHorario());
            }
            map.put(t, subMap );
        }

        return map;
    }

    private Intervalo getMatchIntervalo(String i){
        for(Intervalo t: Intervalo.values()){
            if(i.equals(t.getCode())){
                return t;
            }
        }
        throw new RuntimeException("Codigo de intervalo invalido!");
    }

    public Map<Intervalo, Map<DayEnum, CursoHorario>> mapHorario(){

        List<CursoHorario> horarios = listHorario();
        if(horarios == null){
            System.out.println("HORARIO NULO");
        }
        else{
            System.out.println("HORARIO NAO NULO");
        }
        Map<Intervalo, Map<DayEnum, CursoHorario>> map = createMapTemplate();

        horarios.forEach(h -> {
            Map<DayEnum, CursoHorario> submap = map.get(getMatchIntervalo(h.getintervalo()));
           try{
            CursoHorario ch = submap.get(h.getDay());

            if(ch.getCourse() == null){
               submap.put(h.getDay(), h);
            }
               System.out.println("CONSEGUIU PASSAR");

           }catch(Exception e){
                System.out.println("NAO POSSUI VALOR");
           }

        });

        return map;
    }

    /*
    private Map<DayEnum, Map<String, CursoHorario>> createMap(){
        Map<DayEnum, Map<String, CursoHorario>> map = new HashMap<>();
        for( DayEnum d : DayEnum.values() ){

            Map<String, CursoHorario> subMap = new HashMap<>();
            String[] turnos = {"M1", "M2", "M3", "M4", "M5", "M6", "T1", "T2", "T3", "T4", "T5", "T6", "N1", "N2", "N3", "N4", "N5"};

            for(String t : turnos){
                subMap.put(t, null);
            }

            map.put( d, subMap );
        }
        return map;
    }


    public Map< DayEnum, Map<String, CursoHorario>> mapHorario(){

        Map<DayEnum, Map<String, CursoHorario>> map = createMap();

        List<CursoHorario> horarios = listHorario();

        horarios.forEach(h -> {

             Map<String, CursoHorario> sub = map.get(h.getDay());
            sub.putIfAbsent(h.getintervalo(), h);


        });

        return map;

    }*/

}
