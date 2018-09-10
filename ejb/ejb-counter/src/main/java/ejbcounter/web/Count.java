package ejbcounter.web;

import ejbcounter.ejb.Counter;
import ejbcounter.ejb.CounterBean;
import ejbcounter.qualifier.MyCounter;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ConversationScoped
public class Count implements Serializable {

    private static final Logger logger = Logger.getLogger(CounterBean.class.getName());

    @Inject
    @MyCounter
    private Instance<Counter> counter;

    public long getHit() {
        long hit = counter.get().getHit();
        logger.log(Level.INFO, "getHit called, hit is {0}", hit);
        return hit;
    }
}
