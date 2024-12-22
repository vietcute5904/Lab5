package hust.soict.dsai.test.toString;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import hust.soict.dsai.aims.media.*;

public class toStringTest {
   public static void main(String[] args) {
        // Create a list of Media objects
        List<MediaVhq> mediaList = new ArrayList<>();
        
        mediaList.add(new CompactDiscVhq(1, "Big Bang concert", "Music", "G-Dragon", "Top", 180, 9f));
        mediaList.add(new DVDCngVhq("Dragon Ball", "Anime", 12f));
        mediaList.add(new BookVhq(3, "Book 10000 question why", "hihi", 5f));
        
        // Print before sorting
        System.out.println("Before sorting:");
        for (MediaVhq m : mediaList) {
            System.out.println(m);
        }

        // Sort by title and cost (alphabetical order and descending cost)
        Collections.sort(mediaList, MediaVhq.COMPARE_BY_TITLE_COST);
        
        System.out.println("\nAfter sorting by title and then cost:");
        for (MediaVhq m : mediaList) {
            System.out.println(m);
        }
        
        // Sort by cost and then by title (descending cost and alphabetical order of title)
        Collections.sort(mediaList, MediaVhq.COMPARE_BY_COST_TITLE);
        
        System.out.println("\nAfter sorting by cost and then title:");
        for (MediaVhq m : mediaList) {
            System.out.println(m);
        }
    }
}
