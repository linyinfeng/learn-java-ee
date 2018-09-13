package uploadfiletodatabase.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import uploadfiletodatabase.ejb.FileManager;

@Named
@SessionScoped
public class FileBean implements Serializable {
    
    private static final Logger logger = Logger.getLogger(FileBean.class.getName());

    private static final long serialVersionUID = 1L;
    
    private static Pattern getFileNameExtension = Pattern.compile(".+?//.([a-zA-z]+)");

    @Inject
    private FileManager fm;
    private UploadedFile file;
    private String selectedFile;

    public String getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(String selectedFile) {
        this.selectedFile = selectedFile;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void uploadHandler() throws IOException {
        if (file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            InputStream is = file.getInputstream();
            byte[] bytes = IOUtils.toByteArray(is);
            fm.save(file.getFileName(), bytes);
        }
    }
    
    public List<String> getFileNameList() {
        return fm.getNames();
    }
    
    public StreamedContent getSelectedFileContent() {
        logger.log(Level.INFO, "File: {0}", selectedFile);
        byte[] bytes = fm.load(selectedFile);
        logger.log(Level.INFO, "Get bytes: {0}", bytes);
        InputStream is = new ByteArrayInputStream(bytes);
        Matcher matcher = getFileNameExtension.matcher(selectedFile);
        String ext = "png";
        if (matcher.find()) {
            ext = matcher.group(1);
        }
        return new DefaultStreamedContent(is, "image/" + ext, selectedFile);
    }
}
