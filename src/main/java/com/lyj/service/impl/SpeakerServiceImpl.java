package com.lyj.service.impl;

import com.github.pagehelper.PageHelper;
import com.lyj.dao.SpeakerDao;
import com.lyj.entity.Speaker;
import com.lyj.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SpeakerServiceImpl implements SpeakerService {
    @Autowired
    SpeakerDao speakerDao;

    @Override
    public List<Speaker> findAllSpeakers(Map<String, Integer> map) {
        // 设置页码和每页显示的记录数，该语句后面，紧跟着数据库查询相关的语句
        PageHelper.startPage(map.get("page"), map.get("limit"));
        return speakerDao.findAllSpeakers();
    }

    @Override
    public Speaker selectSpeaker(String id) {
        return speakerDao.selectSpeakerById(Integer.valueOf(id));
    }

    @Override
    public void updateSpeaker(Speaker speaker) {
        speakerDao.updateSpeaker(speaker);
    }

    @Override
    public void addSpeaker(Speaker speaker) {
        speakerDao.addSpeaker(speaker);
    }

    @Override
    public void delete(int[] id) {
        speakerDao.delete(id);
    }
}
