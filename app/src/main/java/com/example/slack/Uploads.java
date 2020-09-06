package com.example.slack;

public class Uploads {
    private String type,name,time,message,profile_image,post_image,vedio_view;

    public Uploads(String type, String name, String time, String message, String profile_image, String post_image, String vedio_view) {
        this.type = type;
        this.name = name;
        this.time = time;
        this.message = message;
        this.profile_image = profile_image;
        this.post_image = post_image;
        this.vedio_view = vedio_view;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getPost_image() {
        return post_image;
    }

    public void setPost_image(String post_image) {
        this.post_image = post_image;
    }

    public String getVedio_view() {
        return vedio_view;
    }

    public void setVedio_view(String vedio_view) {
        this.vedio_view = vedio_view;
    }
}
