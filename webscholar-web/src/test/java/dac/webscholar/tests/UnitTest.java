package dac.webscholar.tests;

import dac.webscholar.Utils.PatternValidator;
import dac.webscholar.Utils.ValidatorType;
import dac.webscholar.cdiqualifiers.LoginProxyQualifier;
import dac.webscholar.cdiqualifiers.PatternValidatorQualifier;
import dac.webscholar.cdiqualifiers.RoomProxyQualifier;
import dac.webscholar.sessionbeans.LoginServiceLocal;
import dac.webscholar.sessionbeans.RoomServiceLocal;
import dac.webscholar.shared.entities.Room;
import dac.webscholar.shared.entities.RoomType;
import dac.webscholar.shared.interfaces.RoomService;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.util.List;

/**
 * Created by marcusviniv on 21/09/2016.
 */

@RunWith(Arquillian.class)
public class UnitTest extends ArquillianTest {

    @Inject
    @PatternValidatorQualifier(type = ValidatorType.EMAIL)
    private PatternValidator emailValidator;

    @Inject
    @LoginProxyQualifier
    private LoginServiceLocal loginService;

    @Inject
    @RoomProxyQualifier
    private RoomServiceLocal roomService;

    @Test
    public void testInsertRoom(){
        Room room = new Room("sala 1", RoomType.ROOM);
        Room room2 = new Room("sala 2", RoomType.LAB);
        roomService.saveRoom(room);
        roomService.saveRoom(room2);

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


    @Test
    public void doLogin() throws LoginException{
        loginService.login("admin@gmail.com", "admin");
    }

    @Test (expected = LoginException.class)
    public void doLoginWrongUser() throws LoginException{
        loginService.login("admin@gmail.com", "dsadsad");
    }

    @Test (expected = LoginException.class)
    public void doLoginInvalidEmail() throws LoginException{
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

}
