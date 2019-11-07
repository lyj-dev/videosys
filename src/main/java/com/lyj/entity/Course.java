package com.lyj.entity;

import java.util.List;

public class Course {
    private String id;
    private String title;
    private String desc;
    private String subjectId;
    private String subject;
    private List<Video> videoList;

    public Course() {}

    public Course(String id, String title, String desc, String subjectId, String subject) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.subjectId = subjectId;
        this.subject = subject;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }
}
