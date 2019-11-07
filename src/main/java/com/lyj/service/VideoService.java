package com.lyj.service;

import com.github.pagehelper.PageHelper;
import com.lyj.entity.Video;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName VideoService
 * @Description TODO
 * @Author lyj
 * @Date 2019/11/7 21:41
 * @Version 1.0
 */
public interface VideoService {

    List<Video> findAllVideo(Map<String, Integer> pageMap, Video video);


    Video selectVideo(String id);


    void addVideo(Video video);


    void updateVideo(Video video);


    void delete(int[] id);
}
