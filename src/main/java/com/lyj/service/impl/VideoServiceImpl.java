package com.lyj.service.impl;

import com.github.pagehelper.PageHelper;
import com.lyj.dao.VideoDao;
import com.lyj.entity.Video;
import com.lyj.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoDao videoDao;

    @Override
    public List<Video> findAllVideo(Map<String, Integer> pageMap, Video video) {
        // 设置页码和每页显示的记录数，该语句后面，紧跟着数据库查询相关的语句
        PageHelper.startPage(pageMap.get("page"), pageMap.get("limit"));
        return videoDao.findAllVideos(video);
    }

    @Override
    public Video selectVideo(String id) {
        return videoDao.selectVideoById(Integer.valueOf(id));
    }

    @Override
    public void addVideo(Video video) {
        videoDao.addVideo(video);

    }

    @Override
    public void updateVideo(Video video) {
        videoDao.updateVideo(video);
    }

    @Override
    public void delete(int[] id) {
        videoDao.delete(id);
    }
}
