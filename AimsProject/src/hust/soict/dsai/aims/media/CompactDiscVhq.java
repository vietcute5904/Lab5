package hust.soict.dsai.aims.media;
import hust.soict.dsai.aims.exception.PlayerExceptionVhq;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.util.ArrayList;

public class CompactDiscVhq extends DiscVhq implements PlayableVhq {
    private String artist;  
    private ArrayList<TrackVhq> tracks;  //ArrayList chứa các đối tượng TrackVhq    

    public void play() throws PlayerExceptionVhq {
        int size = tracks.size();
        if (this.getLengthVhq() <= 0) {
            throw new PlayerExceptionVhq("ERROR: CD length is non-positive");
        }
        for (TrackVhq track : tracks) {
            if (track.getLengthVhq() > 0) {
                JLabel text = new JLabel("CD - Title: " + track.getTitleVhq() + " Length: " + track.getLengthVhq());
            } else {
                throw new PlayerExceptionVhq("ERROR: CD length is non-positive");
            }
        }
        JDialog dialog = new JDialog();
        (dialog).setSize(300, 200);
        dialog.setTitle("Play CD");
        dialog.setVisible(true);
    }
    // Constructor với title và cost
    public CompactDiscVhq(String title, float cost) {
        super(title, cost); 
        this.tracks = new ArrayList<TrackVhq>();  // Khởi tạo danh sách tracks
    }
    // Constructor với các tham số đầy đủ
    public CompactDiscVhq(int id, String title, String category, String author, String artist, int length, float cost) {
        super(id, title, category, author, length, cost); 
        this.artist = artist;  // Gán giá trị cho trường artist
        this.tracks = new ArrayList<TrackVhq>();  // Khởi tạo danh sách tracks
    }
    // Getter cho artist
    public String getArtistVhq() {
        return artist;
    }
    // Phương thức thêm track vào danh sách tracks
    public void addTrackVhq(TrackVhq track) {
        if (track == null) {
            throw new IllegalArgumentException("Track cannot be null.");
        }
        // Kiểm tra xem track đã tồn tại chưa
        if (tracks.contains(track)) {
            System.out.println("Track already exists in the list.");
        } else {
            tracks.add(track);
            System.out.println("Track added.");
        }
    }
    // Phương thức xóa track khỏi danh sách tracks
    public void removeTrackVhq(TrackVhq track) {
        if (track == null) {
            throw new IllegalArgumentException("Track cannot be null.");
        }
        // Kiểm tra xem track có trong danh sách không
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track removed.");
        } else {
            System.out.println("Track not found.");
        }
    }
    // Phương thức tính tổng length của CompactDisc từ các track
    public int getLengthVhq() {
        int totalLength = 0;
        for (TrackVhq track : tracks) {
            totalLength += track.getLengthVhq();  // Cộng tổng length của từng track
        }
        return totalLength;
    }
    @Override
    public String toString() {
        return "CD [" + getTitleVhq() + 
        " - " + getAuthorVhq() + 
        " - " + getArtistVhq() + 
        " - " + tracks.size() + 
        " - " + getLengthVhq() + 
        " - " + getCostVhq() + "]";
    }
}
