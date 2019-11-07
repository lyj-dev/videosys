package com.lyj.controller;

import com.github.pagehelper.Page;
import com.lyj.entity.Course;
import com.lyj.entity.Speaker;
import com.lyj.entity.Video;
import com.lyj.service.CourseService;
import com.lyj.service.SpeakerService;
import com.lyj.service.VideoService;
import com.lyj.utils.PageUtil;
import com.lyj.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SpeakerService speakerService;

    @RequestMapping("/list.do")
    @ResponseBody
    public Map<String, Object> listVideo(Integer page, Integer limit, Video video) {
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("page", page);
        pageMap.put("limit", limit);

        List<Video> list = videoService.findAllVideo(pageMap, video);

        for (Video v : list) {
            v.setTime(TimeUtil.secToTime(Integer.valueOf(v.getTime())));
        }

        long total = ((Page) list).getTotal();

        return PageUtil.pubPage(total, list);
    }

    @RequestMapping("/query.do")
    public String queryVideo(Model model, String id) {
        if (id != null && !id.equals("")) {
            Video video = videoService.selectVideo(id);
            model.addAttribute("video", video);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("page", 1);
        map.put("limit", 65536);
        List<Course> courseList = courseService.findAllCourse(map);
        List<Speaker> speakerList = speakerService.findAllSpeakers(map);

        model.addAttribute("speakerList", speakerList);
        model.addAttribute("courseList", courseList);
        return "/behind/updateVideo";
    }

    @RequestMapping(value = "/addOrUpdate.do")
    @ResponseBody
    public int addOrUpdateVideo(Video video) {
        if (video.getId() == null || video.getId().equals("")) {
            videoService.addVideo(video);
        } else {
            videoService.updateVideo(video);
        }
        return 1;
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public String delete(int[] id) {
        videoService.delete(id);
        return "0";
    }

    @RequestMapping("/showVideo")
    public String showVideo(String videoId, String subjectName, Model model) {

        Video video = videoService.selectVideo(videoId);

        Speaker speaker = speakerService.selectSpeaker(video.getSpeakerId());
        Course course = courseService.selectCourse(video.getCourseId());

        // course对象中所有的video时长time改成xx:xx:xx的形式
        for (int j = 0; j < course.getVideoList().size(); j++) {
            Video tempVideo = course.getVideoList().get(j);
            tempVideo.setTime(TimeUtil.secToTime(Integer.valueOf(tempVideo.getTime())));
            course.getVideoList().set(j, tempVideo);
        }

        model.addAttribute("subjectName", subjectName);
        model.addAttribute("course", course);
        model.addAttribute("speaker", speaker);
        model.addAttribute("video", video);

        return "before/section";
    }
}
