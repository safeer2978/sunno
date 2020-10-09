package com.sunno.Main.Model.Object;


import java.util.ArrayList;
import java.util.List;

public class InnerListObject {
    String title;
    String img_url;
    List<Object> params;

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    int albumId;

    public InnerListObject(String title, String img_url, int type) {
        this.title = title;
        this.img_url = img_url;
        this.type = type;
        params =new ArrayList<>();
    }

    public void setParams(Object o){
        params.add(o);
    }

    public List<Object> getParams() {
        return params;
    }

    public InnerListObject(String title, int type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    int type;
}