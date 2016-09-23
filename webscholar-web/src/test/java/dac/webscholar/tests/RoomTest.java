package dac.webscholar.tests;

import dac.webscholar.Utils.CpfValidator;
import dac.webscholar.Utils.PatternValidator;
import dac.webscholar.Utils.ValidatorType;
import dac.webscholar.cdiqualifiers.LoginProxyQualifier;
import dac.webscholar.cdiqualifiers.PatternValidatorQualifier;
import dac.webscholar.cdiqualifiers.RoomProxyQualifier;
import dac.webscholar.repository.ListStrategy;
import dac.webscholar.sessionbeans.Initializer;
import dac.webscholar.sessionbeans.course.CourseProxyQualifier;
import dac.webscholar.sessionbeans.course.CourseServiceLocal;
import dac.webscholar.sessionbeans.login.LoginProxy;
import dac.webscholar.sessionbeans.login.LoginServiceLocal;
import dac.webscholar.sessionbeans.room.RoomProxy;
import dac.webscholar.sessionbeans.room.RoomServiceLocal;
import dac.webscholar.shared.entities.Course;
import dac.webscholar.shared.entities.Room;
import dac.webscholar.shared.entities.RoomType;
import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.utils.RoleUriMap;
import jsfbeans.LoginMB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.util.List;

/**
 * Created by marcusviniv on 22/09/2016.
 */

@RunWith(Arquillian.class)
public class RoomTest{

    @Deployment
    public static JavaArchive createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage(ScholarUser.class.getPackage())
                .addPackage(RoleUriMap.class.getPackage())
                .addPackage(LoginProxyQualifier.class.getPackage())
                .addPackage(LoginProxy.class.getPackage())
                .addPackage(CourseProxyQualifier.class.getPackage())
                .addPackage(RoomProxy.class.getPackage())
                .addPackage(ListStrategy.class.getPackage())
                .addPackage(Initializer.class.getPackage())
                .addPackage(CpfValidator.class.getPackage())
                .addPackage(LoginMB.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addAsResource("META-INF/persistence.xml");
    }

    @Inject
    @RoomProxyQualifier
    private RoomServiceLocal roomService;


    @Inject
    @PatternValidatorQualifier(type = ValidatorType.EMAIL)
    private PatternValidator emailValidator;

    @Inject
    @LoginProxyQualifier
    private LoginServiceLocal loginService;

    @Test
    public void doLogin() throws LoginException {
        loginService.login("admin@gmail.com", "admin");
    }

    @Test (expected = LoginException.class)
    public void doLoginWrongUser() throws LoginException{
        loginService.login("admin@gmail.com", "dsadsad");
    }

    @Test (expected = LoginException.class)
    public void doLoginInvalidEmail() throws LoginException {
        loginService.login("dkasjdlakje", "dsadsa");
    }

    @Test
    public void validGmail(){
        Assert.assertEquals(true, emailValidator.isValid("admin@gmail.com"));
    }

    @Test
    public void testInvalidEmail(){
        Assert.assertEquals(false, emailValidator.isValid("DALSKJDLASKJDLKWJE"));
    }

    @Test
    public void validHotmail(){
        Assert.assertEquals(true, emailValidator.isValid("vmvini@hotmail.com"));
    }









    //-----------------ROOM TESTS




    private void saveRooms(){
        try{
            Room room = new Room("sala 1", RoomType.ROOM);
            Room room2 = new Room("sala 2", RoomType.LAB);
            roomService.saveRoom(room);
            roomService.saveRoom(room2);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testSearchRoomById(){
        saveRooms();

        List<Room> rooms = roomService.searchByName("sala 1");

        Room found = roomService.searchById(rooms.get(0).getId());
        Assert.assertEquals("sala 1", found.getNome());
    }

    @Test
    public void testSearchRoomByTypeAndName(){
        saveRooms();
        List<Room> rooms;

        rooms = roomService.searchByTypeName("sala 1", RoomType.LAB);
        Assert.assertEquals(0, rooms.size());

        rooms = roomService.searchByTypeName("sala 1", RoomType.ROOM);
        Assert.assertEquals(1, rooms.size());
    }


    @Test
    public void testInsertRoom(){
        saveRooms();

        List<Room> rooms;

        rooms = roomService.searchByName("sala 1");
        Assert.assertEquals(RoomType.ROOM, rooms.get(0).getRoomType());

        rooms = roomService.searchByName("sala 2");
        Assert.assertEquals(RoomType.LAB, rooms.get(0).getRoomType());

        rooms = roomService.searchByType(RoomType.ROOM);
        Assert.assertEquals("sala 1", rooms.get(0).getNome());

        rooms = roomService.searchByType(RoomType.LAB);
        Assert.assertEquals("sala 2", rooms.get(0).getNome());

    }







    //----------------------COURSES TESTS -----------------------------------------

    @Inject
    @CourseProxyQualifier
    private CourseServiceLocal courseService;

    public void insertCourses(){
        try {
            Course course = new Course("ADS", 6);
            Course course2 = new Course("AUTOMACAO", 6);
            courseService.saveCourse(course);
            courseService.saveCourse(course2);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }


    }

    @Test
    public void removeCourse(){

        insertCourses();

        List<Course> courses = courseService.searchByName("ADS");
        courseService.removeCourse(courses.get(0));

        courses = courseService.searchByName("ADS");

        Assert.assertEquals(0, courses.size());
    }

    @Test
    public void findCourses(){
        insertCourses();

        List<Course> courses = courseService.searchByName("AUTOMACAO");
        Course c = courses.get(0);

        Assert.assertEquals(6, c.getSeasons());

    }

    @Test
    public void findCoursesById(){
        insertCourses();

        List<Course> courses = courseService.searchByName("AUTOMACAO");
        Course c = courses.get(0);

        Course course = courseService.searchById(c.getId());
        Assert.assertEquals("AUTOMACAO", course.getName());

    }




}
