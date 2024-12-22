package hust.soict.dsai.aims.screen.store;

import hust.soict.dsai.aims.media.CompactDiscVhq;
import hust.soict.dsai.aims.store.StoreVhq;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCDToStoreScreenVhq extends JFrame {
    private StoreVhq store;

    private JTextField title;
    private JTextField category;
    private JTextField author;
    private JTextField length;
    private JTextField cost;
    private JTextField artist;
    private JButton btnSubmit;
    private JButton btnBack;

    public AddCDToStoreScreenVhq(StoreVhq store) {
        this.store = store;
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(7, 7));

        cp.add(new JLabel("Enter title"));
        title = new JTextField(20);
        cp.add(title);

        cp.add(new JLabel("Enter category"));
        category = new JTextField(20);
        cp.add(category);

        cp.add(new JLabel("Enter author"));
        author = new JTextField(20);
        cp.add(author);

        cp.add(new JLabel("Enter length"));
        length = new JTextField(20);
        cp.add(length);

        cp.add(new JLabel("Enter cost"));
        cost = new JTextField(20);
        cp.add(cost);

        cp.add(new JLabel("Enter artist"));
        artist = new JTextField(20);
        cp.add(artist);

        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StoreScreenVhq(store);
                dispose();
            }
        });
        
        btnSubmit = new JButton("Enter");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String titleString = title.getText();
                    String categoryString = category.getText();
                    String authorString = author.getText();
                    String artistString = artist.getText();
                    int lengthInt = Integer.parseInt(length.getText());
                    float costFloat = Float.parseFloat(cost.getText());
                    CompactDiscVhq cd = new CompactDiscVhq(1, titleString, categoryString, authorString, artistString, lengthInt, costFloat);
                    store.addMediaVhq(cd);
                    new StoreScreenVhq(store);
                    dispose();
                } catch (IllegalArgumentException ev) {
                    JFrame frame = new JFrame();
                    JDialog dialog = new JDialog(frame, "Play media", true);
                    JPanel mainGui = new JPanel(new BorderLayout());

                    mainGui.setBorder(new EmptyBorder(50, 50, 50, 50));
                    mainGui.add(new JLabel("Cost must be float and Length must be integer"));
                    JPanel buttonPanel = new JPanel(new FlowLayout());
                    mainGui.add(buttonPanel, BorderLayout.SOUTH);

                    JButton close = new JButton("Close");
                    close.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dialog.setVisible(false);
                            frame.setVisible(false);
                        }
                    });
                    buttonPanel.add(close);
                    frame.setLocationRelativeTo(null);
                    dialog.setContentPane(mainGui);
                    dialog.pack();
                    dialog.setLocationRelativeTo(frame);
                    dialog.setVisible(true);
                }
            }
        });
        
        cp.add(btnBack);
        cp.add(btnSubmit);

        setTitle("Add CD");
        setSize(600, 240);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
