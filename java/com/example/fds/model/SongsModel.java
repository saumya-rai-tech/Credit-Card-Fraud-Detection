package com.example.fds.model;

public class SongsModel {
    private String thumbnail;
    private String id;
    private String title;
    private String description;
    private String path;
    private String movie_album_name;

    public SongsModel(String thumbnail, String id, String title, String description, String path, String movie_album_name) {
        this.thumbnail = thumbnail;
        this.id = id;
        this.title = title;
        this.description = description;
        this.path = path;
        this.movie_album_name = movie_album_name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMovie_album_name() {
        return movie_album_name;
    }

    public void setMovie_album_name(String movie_album_name) {
        this.movie_album_name = movie_album_name;
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
