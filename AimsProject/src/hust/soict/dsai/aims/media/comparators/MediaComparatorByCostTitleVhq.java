package hust.soict.dsai.aims.media.comparators;

import java.util.Comparator;
import hust.soict.dsai.aims.media.MediaVhq;

public class MediaComparatorByCostTitleVhq implements Comparator<MediaVhq> {
    @Override
    public int compare(MediaVhq m1, MediaVhq m2) {
        // Sắp xếp theo chi phí giảm dần
        int costCompare = Float.compare(m2.getCostVhq(), m1.getCostVhq());        
        // Nếu chi phí giống nhau, sắp xếp theo tiêu đề
        if (costCompare == 0) {
            return m1.getTitleVhq().compareToIgnoreCase(m2.getTitleVhq()); 
        }
        return costCompare;
    }
}
