package com.example.securesport.Actualite;

public class NewsItem {
    private String title;
    private String content;

    public NewsItem(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

