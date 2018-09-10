package ejbcounter.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CounterBean implements Counter {

    private static final Logger logger = Logger.getLogger(CounterBean.class.getName());

    @SuppressWarnings("UnusedAssignment")
    private long hits = 0;

    public CounterBean(long hits) {
        this.hits = hits;
    }

    @Override
    public long getHit() {
        ++hits;
        logger.log(Level.INFO, "Current hits: {0}", hits);
        return hits;
    }
}
