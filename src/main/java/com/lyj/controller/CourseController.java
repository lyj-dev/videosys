package com.lyj.controller;

import com.github.pagehelper.Page;
import com.lyj.entity.Course;
import com.lyj.entity.Subject;
import com.lyj.service.CourseService;
import com.lyj.service.SubjectService;
import com.lyj.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    SubjectService subjectService;

    @RequestMapping("/list.do")
    @ResponseBody
    public Map<String, Object> listCourse(Integer page, Integer limit) {
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("page", page);
        pageMap.put("limit", limit);

        List<Course> list = courseService.findAllCourse(pageMap);

        long total = ((Page) list).getTotal();

        return PageUtil.pubPage(total, list);
    }

    @RequestMapping("/query.do")
    public String queryCourse(Model model, String id) {
        Course course = null;
        if (id != null && !id.equals("")) {
             course = courseService.selectCourse(id);
        }
        List<Subject> subjectList = subjectService.findAllSubjects();
        model.addAttribute("course", course);
        model.addAttribute("subjectList", subjectList);
        return "/behind/updateCourse";
    }

    @RequestMapping(value = "/addOrUpdate.do")
    @ResponseBody
    public int addOrUpdateCourse(Course course) {
        if (course.getId() == null || course.getId().equals("")) {
            courseService.addCourse(course);
        } else {
            courseService.updateCourse(course);
        }
        return 1;
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public String delete(int[] id) {
        courseService.delete(id);
        return "0";
    }

    @RequestMapping("/page.do")
    public String mainPage() {
//        List<Course>
        return null;
    }
}
