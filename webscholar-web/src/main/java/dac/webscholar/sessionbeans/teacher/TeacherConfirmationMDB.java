package dac.webscholar.sessionbeans.teacher;

import dac.webscholar.shared.entities.Teacher;
import dac.webscholar.shared.exceptions.ValidationException;
import dac.webscholar.shared.interfaces.TeacherService;
import jsfbeans.MailSender;

import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by marcusviniv on 25/09/2016.
 */

@Named
@MessageDriven(mappedName = "jms/emailQueue")
public class TeacherConfirmationMDB  implements MessageListener{

    @PersistenceContext
    private EntityManager em;

    @Inject
    private MailSender emailSender;

    @Override
    public void onMessage(Message message) {
        try{
            ObjectMessage om = (ObjectMessage) message;
            Teacher teacher = (Teacher) om.getObject();
            teacher.setActivated(true);
            em.merge(teacher);

            emailSender.sendMail(teacher.getEmail(), "Confirmação de cadastro no WebScholar", "Bem vindo ao WebScholar, " + teacher.getName());
        }
        catch(JMSException  e){
            e.printStackTrace();
        }

    }

}
