package com.sunno.Main.Model.Request;

public class PlayRequest {
    String track_id;

    public PlayRequest(String track_id) {
        this.track_id = track_id;
    }

    public String getTrack_id() {
        return track_id;
    }

    public void setTrack_id(String track_id) {
        this.track_id = track_id;
    }
}
