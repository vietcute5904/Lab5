package hust.soict.dsai.aims.screen.store;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hust.soict.dsai.aims.cart.CartVhq;
import hust.soict.dsai.aims.media.MediaVhq;
import hust.soict.dsai.aims.media.PlayableVhq;

public class MediaStoreVhq extends JPanel {
    private MediaVhq media;
    private CartVhq cart;

    public MediaStoreVhq(MediaVhq media, CartVhq cart) {
        this.media = media;
        this.cart = cart;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitleVhq());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCostVhq() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Nút "Add to cart"
        JButton addToCartBtn = new JButton("Add to cart");
        addToCartBtn.addActionListener(e -> handleAddToCart());
        container.add(addToCartBtn);

        // Nút "Play" nếu media là PlayableVhq
        if (media instanceof PlayableVhq) {
            JButton playBtn = new JButton("Play");
            playBtn.addActionListener(e -> handlePlay());
            container.add(playBtn);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);
        this.add(Box.createVerticalGlue());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void handleAddToCart() {
        cart.addMediaVhq(media);

        // Hiển thị hộp thoại thông báo
        JOptionPane.showMessageDialog(
            null,
            "Added to cart: " + media.getTitleVhq(),
            "Notification",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void handlePlay() {
        if (media instanceof PlayableVhq) {
            PlayableVhq playable = (PlayableVhq) media;
            JDialog dialog = new JDialog();
            dialog.setTitle("Playing Media");
            dialog.setSize(300, 200);
            dialog.setLayout(new FlowLayout());

            JLabel label = new JLabel("Playing: " + media.getTitleVhq());
            label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 16));
            dialog.add(label);

            dialog.setVisible(true);
        }
    }
}
