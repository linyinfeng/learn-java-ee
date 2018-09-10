package ejbcounter.ejb;

import java.io.Serializable;

public interface Counter extends Serializable {
    long getHit();
}
