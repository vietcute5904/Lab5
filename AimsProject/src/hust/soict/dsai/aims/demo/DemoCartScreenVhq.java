package hust.soict.dsai.aims.demo;

import javax.swing.*;
import hust.soict.dsai.aims.cart.CartVietHQ;
import hust.soict.dsai.aims.screen.cart.CartScreenVhq;
import hust.soict.dsai.aims.media.*;

public class DemoCartScreenVhq {
    public static void main(String[] args) {
        CartVietHQ cart = new CartVietHQ();

        MediaVhq m1 = new DVDVhq("Doraemon", "Anime", 15f);
        MediaVhq m2 = new BookVhq(0, "Why we sleep", "Scientific", 9f);
        MediaVhq m3 = new DVDVhq("Gao ranger", "Super sentai",  2);
        MediaVhq m4 = new CompactDiscVhq( 1, "Conan", "Anime", "Hihi", "Shinichi", 5,  10);

        cart.addMediaVhq(m1);
        cart.addMediaVhq(m2);
        cart.addMediaVhq(m3);
        cart.addMediaVhq(m4);

        CartScreenVhq cartScreen = new CartScreenVhq(cart);

        cartScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
