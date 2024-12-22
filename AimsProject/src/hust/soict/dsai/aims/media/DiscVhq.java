package hust.soict.dsai.aims.media;

public class DiscVhq extends MediaVhq  {
    private int length;
    private String author;

    // Constructor đầy đủ
    public DiscVhq(int id, String title, String category, String author, int length, float cost) {
        super(id, title, category, cost); // Gọi constructor lớp Media
        this.length = length;
        this.author = author;
    }

    public DiscVhq(String title, float cost) {
        super(title, cost);
    }

    // Getters
    public int getLengthVhq() {
        return length;
    }

    public String getAuthorVhq() {
        return author;
    }

    // Setter
    public void setLengthVhq(int length) {
        this.length = length;
    }

    public void setAuthorVhq(String author) {
        this.author = author;
    }
}
