package ejbcart.util;

public class IdVerifier {

    public IdVerifier() {
    }

    public boolean validate(String id) {
        boolean result = true;

        for (int i = 0; i < id.length(); i++) {
            if (!Character.isDigit(id.charAt(i))) {
                result = false;
            }
        }

        return result;
    }
}
