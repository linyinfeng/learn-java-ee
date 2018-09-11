package primefacesplayground;

import org.primefaces.event.SelectEvent;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@SessionScoped
public class DateSelectHandler implements Serializable {

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void handleDateSelect(SelectEvent event) {
        Date eventDate = (Date) event.getObject();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Date got: " + eventDate));
    }
}
