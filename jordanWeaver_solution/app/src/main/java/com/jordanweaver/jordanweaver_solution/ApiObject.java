package com.jordanweaver.jordanweaver_solution;

import java.io.Serializable;

/**
 * Created by jordanweaver on 1/20/16.
 */
public class ApiObject implements Serializable {

    String title;
    String content;
    String date;

    public ApiObject(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    @Override
    public String toString() {
        return content;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
