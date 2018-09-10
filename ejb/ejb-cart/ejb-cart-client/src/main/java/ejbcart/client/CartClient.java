package ejbcart.client;

import ejbcart.ejb.face.Cart;
import ejbcart.util.BookException;

import javax.ejb.EJB;
import java.util.List;

public class CartClient {
    @EJB
    private static Cart cart;

    private CartClient(String[] args) {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CartClient client = new CartClient(args);
        client.doTest();
    }

    private void doTest() {
        try {
            cart.initialize("Duke d'Url", "123");
            cart.addBook("Infinite Jest");
            cart.addBook("Bel Canto");
            cart.addBook("Kafka on the Shore");

            List<String> bookList = cart.getContents();

            for (String title : bookList) {
                System.out.println("Retrieving book title from cart: " + title);
            }

            System.out.println("Removing \"Gravity's Rainbow\" from cart.");
            cart.removeBook("Gravity's Rainbow");
            cart.remove();

            System.exit(0);
        } catch (BookException ex) {
            System.err.println("Caught a BookException: " + ex.getMessage());
            System.exit(0);
        }
    }
}
