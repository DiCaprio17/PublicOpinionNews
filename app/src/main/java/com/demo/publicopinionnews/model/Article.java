package com.demo.publicopinionnews.model;

import cn.bmob.v3.BmobObject;

public class Article extends BmobObject {
    private String title;
    private String content;

    public Article() {
        this.setTableName("Article");
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
