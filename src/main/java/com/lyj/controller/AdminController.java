package com.lyj.controller;

import com.lyj.entity.Admin;
import com.lyj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login.do")
    @ResponseBody
    public int login(Admin admin, HttpSession session) {
        Admin resultAdmin = adminService.login(admin);
        if (resultAdmin == null || resultAdmin.equals("")) {
            return 0;
        } else {
            session.setAttribute("admin", resultAdmin);
            return 1;
        }
    }

    @RequestMapping("/query.do")
    @ResponseBody
    public Map<String, Object> query(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "1");
        Admin admin = (Admin)session.getAttribute("admin");
        map.put("username", admin.getUsername());
        return map;
    }

    @RequestMapping("/logout.do")
    public String logout(HttpSession session) {

        session.invalidate();
        return "redirect:/behind/login.jsp";
    }
}

