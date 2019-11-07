package com.lyj.service;

import com.lyj.entity.Speaker;

import java.util.List;
import java.util.Map;

public interface SpeakerService {
    List<Speaker> findAllSpeakers(Map<String, Integer> map);

    Speaker selectSpeaker(String id);

    void updateSpeaker(Speaker speaker);

    void addSpeaker(Speaker speaker);

    void delete(int[] id);
}
