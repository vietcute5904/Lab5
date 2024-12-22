package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerExceptionVhq;

public class DVDViethq extends DiscVhq implements PlayableVhq {
    private static int nbDigitalVideoDiscsVhq = 0;

    public void play() throws PlayerExceptionVhq {
        if (this.getLengthVhq() <= 0) {
            // Print error message to the system
            System.err.println("Error: The length of the media is invalid (<= 0). Cannot play the media: " + this.getTitleVhq());
            // Raise the PlayerException
            throw new PlayerExceptionVhq("Cannot play the media. Length is less than or equal to 0.");
        }

        // If length is valid, proceed with playing the media (simulation)
        System.out.println("Playing " + this.getTitleVhq());
    }

    // Constructor với tự động tạo id
    public DVDViethq(String title) {
        super(++nbDigitalVideoDiscsVhq, title, null, null, 0, 0.0f);
    }

    // Constructor chỉ với title và cost
    public DVDViethq(String title, float cost) {
        super(title, cost); // Gọi constructor của DiscVhq
    }

    // Constructor chỉ với title, category và cost
    public DVDViethq(String title, String category, float cost) {
        this(title, cost);
        this.setCategoryVhq(category);
    }

    // Constructor chỉ với title, category, author và cost
    public DVDViethq(String title, String category, String author, float cost) {
        this(title, category, cost);
        this.setAuthorVhq(author);
    }

    // Constructor chỉ với title, category, author, length và cost
    public DVDViethq(String title, String category, String author, int length, float cost) {
        this(title, category, author, cost);
        this.setLengthVhq(length);
    }

    // Phương thức kiểm tra khớp tiêu đề
    public boolean isMatch(String title) {
        return this.getTitleVhq().equalsIgnoreCase(title); // So sánh tiêu đề không phân biệt chữ hoa/thường
    }

    @Override
    public String toString() {
        return "DVD [" + getTitleVhq() + 
        " - " + getCategoryVhq() + 
        " - " + getAuthorVhq() + 
        " - " + getLengthVhq() + 
        " - " + getCostVhq() + "]";
    }
}
