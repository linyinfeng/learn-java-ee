package uploadfiletodatabase.entiry;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "file_storage")
@NamedQuery(name = "findAllFileName", query = "SELECT f.name FROM FileStorage f")
@NamedQuery(name = "findWithName", query = "SELECT f FROM FileStorage f WHERE f.name = :name")
public class FileStorage implements Serializable {

    private static final long serialVersionUID = 1L;

    public FileStorage() {
        this.file = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 255, unique = true)
    String name;

    @Lob
    byte[] file;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FileStorage)) {
            return false;
        }
        FileStorage other = (FileStorage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "uploadfiletodatabase.entiry.Image[ id=" + id + " ]";
    }

}
