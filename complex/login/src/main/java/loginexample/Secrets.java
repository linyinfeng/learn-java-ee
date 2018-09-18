package loginexample;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Named;

@Named
@Stateless
public class Secrets implements Serializable {
    
    private String secretMessage;
    
    @PostConstruct
    void init() {
        this.secretMessage = "It's a secret message.";
    }

    @RolesAllowed("LearnJavaEEUser")
    public String getSecretMessage() {
        return secretMessage;
    }
}
