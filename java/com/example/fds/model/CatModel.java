package com.example.fds.model;

public class CatModel {
    private String thumbnail;
    private String id;
    private String title;
    private String description;

        public CatModel(String thumbnail, String id, String title, String des) {
            this.thumbnail = thumbnail;
            this.id = id;
            this.title = title;
            this.description = des;
        }


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
