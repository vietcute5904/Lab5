package hust.soict.dsai.aims.screen.cart;
import java.io.IOException;
import javax.swing.JFrame;
import hust.soict.dsai.aims.cart.CartVhq;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CartScreenVhq extends JFrame {
    private CartVhq cart;

    public CartScreenVhq(CartVhq cart) {
        super();
        this.cart = cart;
        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);
        this.setTitle("Cart");
        this.setVisible(true);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/hust/soict/dsai/aims/screen/cart/CartVhq.fxml"));
                    loader.setController(new CartScreenControllerVhq(cart));
                    Parent root = loader.load();
                    fxPanel.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
