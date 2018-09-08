package ejbhellobean;

import javax.ejb.Remote;
import java.util.List;

@SuppressWarnings("unused")
@Remote
public interface HelloBean {

    void hello(String message);

    List<String> helloHistory();
}