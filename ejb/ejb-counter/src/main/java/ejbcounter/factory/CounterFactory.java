package ejbcounter.factory;

import ejbcounter.ejb.Counter;
import ejbcounter.ejb.CounterBean;
import ejbcounter.qualifier.MyCounter;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.io.Serializable;

@Dependent
public class CounterFactory implements Serializable {

    private long hits = 0;

    public CounterFactory setHits(long hits) {
        this.hits = hits;
        return this;
    }

    @Produces
    @Singleton
    @MyCounter
    public Counter build() {
        return new CounterBean(hits);
    }
}
