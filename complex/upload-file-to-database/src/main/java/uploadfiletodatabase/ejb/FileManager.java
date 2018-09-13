package uploadfiletodatabase.ejb;

import java.util.List;

public interface FileManager {

    void save(String name, byte[] bytes);

    byte[] load(String name);

    List<String> getNames();
}
