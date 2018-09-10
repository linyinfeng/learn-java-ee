package jmssimplemessageclient;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleMessageClient {

    private static final Logger logger = Logger.getLogger(SimpleMessageClient.class.getName());

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private static ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/LearnJavaEEQueue")
    private static Queue queue;

    public static void main(String[] args) {
        final int NUM_MSGS = 3;
        try (JMSContext context = connectionFactory.createContext()) {
            for (int i = 0; i < NUM_MSGS; ++i) {
                String text = "This is message " + (i + 1);
                logger.log(Level.INFO, "Sending message: {0}", text);
                context.createProducer().send(queue, text);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred: {0}", e);
        }
    }
}
