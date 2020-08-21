package com.sunno;



public class InnerListObject {
    String title;
    String img_url;

    public InnerListObject(String title, String img_url, int type) {
        this.title = title;
        this.img_url = img_url;
        this.type = type;
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