package com.lyj.controller;

import com.lyj.entity.Course;
import com.lyj.entity.Subject;
import com.lyj.entity.Video;
import com.lyj.service.CourseService;
import com.lyj.service.SubjectService;
import com.lyj.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;
    @Autowired
    CourseService courseService;

    @RequestMapping("/list.do")
    @ResponseBody
    public List<Subject> listSubject() {
        return subjectService.findAllSubjects();
    }

    @RequestMapping("/query.do")
    public String querySubject(int id, Model model) {
        Subject subject = subjectService.query(id);

        // 因为subject中的courseList中的course没有video对象
        // 所以将courseList中的每一个course都重新进行courseY与video一对多的嵌套查询
        for (int i = 0; i < subject.getCourseList().size(); i++) {
            Course temp = subject.getCourseList().get(i);
            Course course = courseService.selectCourse(temp.getId());

            // course对象中所有的video时长time改成xx:xx:xx的形式
            for (int j = 0; j < course.getVideoList().size(); j++) {
                Video video = course.getVideoList().get(j);
                video.setTime(TimeUtil.secToTime(Integer.valueOf(video.getTime())));
                course.getVideoList().set(j, video);
            }
            subject.getCourseList().set(i, course);
        }

        model.addAttribute("subject", subject);
        return "before/course";
    }
}
