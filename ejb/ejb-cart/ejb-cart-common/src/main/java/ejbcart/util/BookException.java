package ejbcart.util;

public class BookException extends Exception {
    
    private static final long serialVersionUID = 5206257987274035507L;

    public BookException() {
    }

    public BookException(String msg) {
        super(msg);
    }
}
