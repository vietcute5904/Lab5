package hust.soict.dsai.aims.screen.store;

import hust.soict.dsai.aims.cart.CartVietHQ;
import hust.soict.dsai.aims.media.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import hust.soict.dsai.aims.screen.cart.CartScreenVhq;
import hust.soict.dsai.aims.store.StoreVietHQ;

public class StoreScreenVhq extends JFrame {
    private static final long serialVersionUID = 1L;
    private StoreVietHQ store;
    private CartVietHQ cart;

    public StoreScreenVhq(StoreVietHQ store) {
        this.store = store;
        cart = new CartVietHQ();  // Khởi tạo giỏ hàng

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
    
        cp.add(createNorthVhq(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
    
        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
    }

    JPanel createNorthVhq() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBarVhq());
        north.add(createHeaderVhq());
        return north;
    }

    JMenuBar createMenuBarVhq() {
        JMenu menu = new JMenu("Options");
        
        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBook = new JMenuItem("Add Book");
        JMenuItem addCD = new JMenuItem("Add CD");
        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddDVDToStoreScreenVhq(store);
                dispose();
            }
        });
        addCD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCDToStoreScreenVhq(store);
                dispose();
            }
        });
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBookToStoreScreenVhq(store);
                dispose();
            }
        });

        smUpdateStore.add(addBook);
        smUpdateStore.add(addCD);
        smUpdateStore.add(addDVD);
        menu.add(smUpdateStore);
        menu.add(new JMenuItem("View store"));

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        
        return menuBar;
    }

    JPanel createHeaderVhq() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS VIETHQ 20225849");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cart = new JButton("View cart");
        cart.setPreferredSize(new Dimension(100, 50));
        cart.setMaximumSize(new Dimension(100, 50));
        cart.addActionListener(e -> new CartScreenVhq(this.cart)); // Mở giao diện Cart

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cart);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(0, 3, 2, 2));  // Tự động điều chỉnh số hàng dựa trên số lượng media
    
        ArrayList<MediaVhq> mediaInStore = store.getItemsInStoreVhq();
    
        // Duyệt qua tất cả media trong cửa hàng
        for (int i = 0; i < mediaInStore.size(); i++) {
            MediaStoreVhq cell = new MediaStoreVhq(mediaInStore.get(i), cart);
            center.add(cell);
        }
    
        return center;
    }
}
