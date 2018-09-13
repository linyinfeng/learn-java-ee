package uploadfiletodatabase.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import uploadfiletodatabase.entiry.FileStorage;

@Stateless
public class FileManagerBean implements FileManager {

    @PersistenceContext
    EntityManager em;

    @Override
    public void save(String name, byte[] bytes) {
        FileStorage file = new FileStorage();
        file.setName(name);
        file.setFile(bytes);
        em.persist(file);
    }

    @Override
    public byte[] load(String name) {
        TypedQuery<FileStorage> query = em.createNamedQuery("findWithName", FileStorage.class)
                .setParameter("name", name);
        List<FileStorage> results = query.getResultList();
        if (results != null && results.size() == 1) {
            return results.get(0).getFile();
        } else {
            return null;
        }
        
    }

    @Override
    public List<String> getNames() {
        return em.createNamedQuery("findAllFileName", String.class).getResultList();
    }
}
