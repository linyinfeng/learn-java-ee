package jmssimplemessageejb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/LearnJavaEEQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
})
public class SimpleMessageBean implements MessageListener {

    private static final Logger logger = Logger.getLogger(SimpleMessageBean.class.getName());
    @Resource
    private MessageDrivenContext mdc;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                logger.log(Level.INFO, "Message received: {0}", message.getBody(String.class));
            } else {
                logger.log(Level.WARNING, "Message of wrong type: {0}", message.getClass().getName());
            }
        } catch (JMSException e) {
            logger.log(Level.SEVERE, "JMSException: {0}", e);
            mdc.setRollbackOnly();
        }
    }
}
