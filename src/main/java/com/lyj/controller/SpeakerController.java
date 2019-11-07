package com.lyj.controller;

import com.github.pagehelper.Page;
import com.lyj.entity.Speaker;
import com.lyj.service.SpeakerService;
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
@RequestMapping("/speaker")
public class SpeakerController {
    @Autowired
    SpeakerService speakerService;

    @RequestMapping("/list.do")
    @ResponseBody
    public Map<String, Object> listSpeaker(Integer page, Integer limit) {
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("page", page);
        pageMap.put("limit", limit);

        List<Speaker> list = speakerService.findAllSpeakers(pageMap);

        long total = ((Page) list).getTotal();

//        Map<String, Object> map = new HashMap<>();
//        map.put("code", 0); // 结合layui的表格组件，0表示成功
//        map.put("msg", "");
//        map.put("count", total);// 表中总记录数
//        map.put("data", list); // 获取到的分页数据
        return PageUtil.pubPage(total, list);
    }

    @RequestMapping("/query.do")
    public String querySpeaker(Model model, String id) {
        Speaker speaker = speakerService.selectSpeaker(id);
        model.addAttribute("speaker", speaker);
        return "/behind/updateSpeaker";
    }

    @RequestMapping(value = "/addOrUpdate.do")
    @ResponseBody
    public int addOrUpdateSpeaker(Speaker speaker) {
        if (speaker.getId() == null || speaker.getId().equals("")) {
            speakerService.addSpeaker(speaker);
        } else {
            speakerService.updateSpeaker(speaker);
        }
        return 1;
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public String delete(int[] id) {
        speakerService.delete(id);
        return "0";
    }
}
