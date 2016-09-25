package dac.webscholar.sessionbeans.teacher;

import dac.webscholar.shared.entities.Teacher;
import dac.webscholar.shared.exceptions.ValidationException;
import dac.webscholar.shared.interfaces.TeacherService;

import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

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
