package firstcup.ejb;

import firstcup.entity.FirstCupUser;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Stateless
public class DefaultDukesBirthdayManager implements DukesBirthdayManager {

    private static Logger logger = Logger.getLogger(DefaultDukesBirthdayManager.class.getName());

    @NotNull
    private Optional<Integer> dukesAge = Optional.empty();

    @PersistenceContext(unitName = "com.linyinfeng.projects.first-cpu-client")
    private EntityManager em;

    @Produces
    @PostConstruct
    public void init() {
        this.fetchDukesAge();
        logger.log(Level.INFO, "DefaultDukesBirthdayManager initialized");
    }

    public void fetchDukesAge() {
        Client client = null;
        try {
            client = ClientBuilder.newClient();
            WebTarget target = client.target("http://localhost:8080/dukes-age/webapi/dukesAge");
            String response = target.request().get(String.class);
            int age = Integer.parseInt(response);
            this.setDukesAge(age);
            logger.log(Level.INFO, "Duke''s age set to {0}", age);
        } catch (IllegalArgumentException | NullPointerException | WebApplicationException ex) {
            logger.log(Level.SEVERE, "processing of HTTP response failed", ex);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    @Override
    public void saveUserBirthday(Date birthday) {
        FirstCupUser data = new FirstCupUser(birthday, getAgeDifferenceToDuke(birthday));
        em.persist(data);
    }

    @Override
    public int getAgeDifferenceToDuke(Date birthday) {
        logger.log(Level.SEVERE, "user birthday is {0}", birthday);
        int age = dateToAge(birthday);
        logger.log(Level.SEVERE, "user age is {0}", age);
        return age - this.getDukesAge();
    }

    @Override
    public double getAverageAgeDifference() {
        Query query = em.createNamedQuery("findAverageAgeDifference");
        logger.log(Level.INFO, "Average age difference query is: {0}", query);
        Double avgAgeDiff = (Double) query.getSingleResult();
        logger.log(Level.INFO, "Average age difference is: {0}", avgAgeDiff);
        if (avgAgeDiff == null) {
            avgAgeDiff = 0.0;
        }
        return avgAgeDiff;
    }

    @Override
    public int getDukesAge() {
        if (this.dukesAge.isPresent()) {
            return this.dukesAge.get();
        } else {
            throw new WebApplicationException("Failed to fetch Duke's age");
        }
    }

    private void setDukesAge(Integer age) {
        this.dukesAge = Optional.ofNullable(age);
    }

    private static int dateToAge(Date date) {
        Calendar birthday = new GregorianCalendar();
        birthday.setTime(date);
        Calendar now = GregorianCalendar.getInstance();

        int age = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        birthday.add(Calendar.YEAR, age);

        if (now.before(birthday)) {
            age--;
        }
        return age;
    }
}
