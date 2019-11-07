package com.lyj.entity;

public class Speaker {
    private String id;
    private String name;
    private String desc;
    private String job;
    private String headImgUrl;

    public Speaker() {}

    public Speaker(String id, String name, String desc, String job, String headImgUrl) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.job = job;
        this.headImgUrl = headImgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
}
