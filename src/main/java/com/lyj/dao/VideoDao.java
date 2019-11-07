package com.lyj.dao;

import com.lyj.entity.Video;

import java.util.List;

public interface VideoDao {
    List<Video> findAllVideos(Video video);

    Video selectVideoById(Integer valueOf);

    void addVideo(Video v);

    void updateVideo(Video v);

    void delete(int[] id);
}
