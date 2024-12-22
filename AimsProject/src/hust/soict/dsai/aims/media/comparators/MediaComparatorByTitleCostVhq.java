package hust.soict.dsai.aims.media.comparators;

import java.util.Comparator;
import hust.soict.dsai.aims.media.MediaVhq;

public class MediaComparatorByTitleCostVhq implements Comparator<MediaVhq> {
    @Override
    public int compare(MediaVhq m1, MediaVhq m2) {
        // Sắp xếp theo tiêu đề
        int titleCompare = m1.getTitleVhq().compareToIgnoreCase(m2.getTitleVhq());
        // Nếu tiêu đề giống nhau, sắp xếp theo chi phí giảm dần
        if (titleCompare == 0) {
            return Float.compare(m2.getCostVhq(), m1.getCostVhq()); 
        }
        return titleCompare;
    }
}
