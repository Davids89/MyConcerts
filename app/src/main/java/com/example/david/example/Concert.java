package com.example.david.example;

/**
 * Created by David on 31/8/15.
 */
public class Concert {
    private String title;
    private String link;
    private String imageLink;

    public Concert(String title, String link, String imageLink) {
        this.title = title;
        this.link = link;
        this.imageLink = imageLink;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getImageLink() {
        return imageLink;
    }
}
