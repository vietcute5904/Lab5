package hust.soict.dsai.aims.demo;
import javax.swing.*;

import hust.soict.dsai.aims.cart.CartVietHQ;
import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.screen.store.*;
import hust.soict.dsai.aims.store.*;

public class DemoStoreScreenVhq {
    public static void main(String[] args) {
        StoreVietHQ store = new StoreVietHQ();
        CartVietHQ cart = new CartVietHQ();

        MediaVhq m1 = new DVDVhq("Doraemon", "Anime", "Vietdeptrai", 50, 15f);
        MediaVhq m2 = new BookVhq(0, "10000 questions WHY", "psychology", 17f);
        MediaVhq m3 = new CompactDiscVhq(1, "Deka ranger", "Super sentai", "Haideptrung", "RedDeka", 2, 19f);
        MediaVhq m4 = new DiscVhq(2, "One piece", "Anime", "Oda", 10, 99f);
        MediaVhq m5 = new DVDVhq("Naruto", "Anime", 20f);
        MediaVhq m6 = new BookVhq(2, "Atomic Habits", "Self-Help", 12.99f);
        MediaVhq m7 = new DVDVhq("Star Wars: Episode IV - A New Hope", "Sci-Fi", 7.99f);
        MediaVhq m8 = new CompactDiscVhq(3, "Coldplay - Parachutes", "Rock", "Coldplay", "Chris Martin", 45, 14.99f);
        MediaVhq m9 = new BookVhq(4, "The Lean Startup", "Business", 15.25f);

        store.addMediaVhq(m1);
        store.addMediaVhq(m2);
        store.addMediaVhq(m3);
        store.addMediaVhq(m4);
        store.addMediaVhq(m5);
        store.addMediaVhq(m6);
        store.addMediaVhq(m7);
        store.addMediaVhq(m8);
        store.addMediaVhq(m9);

        StoreScreenVhq storeScreen = new StoreScreenVhq(store);

        storeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
