package primefacesplayground;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AutoCompleteHandler implements Serializable {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> complete(String query) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            results.add(query + i);
        }
        return results;
    }
}
