package dac.webscholar.sessionbeans.teacher;

import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by marcusviniv on 25/09/2016.
 */

@Named
@MessageDriven(mappedName = "jms/teacherQueue")
public class NewTeacherMDB implements MessageListener {

    @Inject
    @TeacherRequestMessage
    Event<Message> jmsEvent;

    @Override
    public void onMessage(Message message) {

        System.out.println("mdb recebe mensagem: " + message);

        jmsEvent.fire(message);

    }
}
