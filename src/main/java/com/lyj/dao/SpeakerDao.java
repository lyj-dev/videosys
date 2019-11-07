package com.lyj.dao;

import com.lyj.entity.Speaker;

import java.util.List;

public interface SpeakerDao {
    List<Speaker> findAllSpeakers();

    Speaker selectSpeakerById(int id);

    void updateSpeaker(Speaker speaker);

    void addSpeaker(Speaker speaker);

    void delete(int[] id);
}
