package com.sunno.Main.Model.Object;

import com.sunno.Main.Model.Object.InnerListObject;

import java.util.List;

public class OuterListObject {
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<InnerListObject> getInnerListObjectList() {
        return innerListObjectList;
    }

    public void setInnerListObjectList(List<InnerListObject> innerListObjectList) {
        this.innerListObjectList = innerListObjectList;
    }

    public OuterListObject(String title) {
        this.title = title;
    }

    List<InnerListObject> innerListObjectList;
}
