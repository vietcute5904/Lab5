package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerExceptionVhq;

public interface PlayableVhq {
    // Phương thức trừu tượng, không có thân phương thức
    public void play() throws PlayerExceptionVhq;
}
