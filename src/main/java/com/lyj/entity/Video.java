package com.lyj.entity;

public class Video {
    private String id;
    private String title;
    private String detail;
    private String time;
    private String speakerId;
    private String speaker;
    private String courseId;
    private String course;
    private String videoUrl;
    private String imgUrl;
    private String playNum;
    private String comment;

    public Video() {

    }

    public Video(String id, String title, String detail, String time,
                 String speakerId, String speaker, String courseId,
                 String course, String videoUrl, String imgUrl, String playNum, String commnet) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.time = time;
        this.speakerId = speakerId;
        this.speaker = speaker;
        this.courseId = courseId;
        this.course = course;
        this.videoUrl = videoUrl;
        this.imgUrl = imgUrl;
        this.playNum = playNum;
        this.comment = commnet;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(String speakerId) {
        this.speakerId = speakerId;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPlayNum() {
        return playNum;
    }

    public void setPlayNum(String playNum) {
        this.playNum = playNum;
    }
}
