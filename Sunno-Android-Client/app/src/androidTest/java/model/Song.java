package model;

public class Song {
    private int song_id;
    private String song_name;
    private String song_genre;
    private String song_artist;

    public Song(int song_id, String song_name, String song_genre, String song_artist) {
        this.song_id = song_id;
        this.song_name = song_name;
        this.song_genre = song_genre;
        this.song_artist = song_artist;
    }

    public Song() {
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getSong_genre() {
        return song_genre;
    }

    public void setSong_genre(String song_genre) {
        this.song_genre = song_genre;
    }

    public String getSong_artist() {
        return song_artist;
    }

    public void setSong_artist(String song_artist) {
        this.song_artist = song_artist;
    }
}
