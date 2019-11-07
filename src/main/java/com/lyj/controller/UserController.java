package com.lyj.controller;

import com.lyj.entity.User;
import com.lyj.service.UserService;
import com.lyj.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    public int login(User user, HttpSession session) {
        user.setPassword(MD5Utils.getMD5(user.getPassword()));
        User resUser = userService.selectUser(user);
        if (resUser != null) {
            session.setAttribute("userAccount", user.getEmail());
            session.setAttribute("user", resUser);
            return 1;
        } else {
            return 0;
        }
    }


    @RequestMapping("/logout.do")
    public String logout(HttpSession session) {
        session.removeAttribute("userAccount");
        session.removeAttribute("user");
        return "before/index";
    }

    /**
     * 判断邮箱是否可用
     * @param email
     * @return 如果邮箱已经被注册，不能被使用则返回1，否则返回0
     */
    @RequestMapping("/validateEmail")
    @ResponseBody
    public int validateEmail(String email){
        return userService.emailExist(email);
    }

    @RequestMapping("/insert.do")
    @ResponseBody
    public int insert(User user, HttpSession session) {
        user.setPassword(MD5Utils.getMD5(user.getPassword()));
        userService.insert(user);
        User resUser = userService.selectUser(user);
        session.setAttribute("userAccount", resUser.getEmail());
        session.setAttribute("user", resUser);
        return 1;
    }

    @RequestMapping("/update.do")
    public String update(User user, HttpSession session, Model model) {
        user.setPassword(MD5Utils.getMD5(user.getPassword()));
        userService.update(user);
        User newUser = userService.selectUser(user);
        session.setAttribute("user", newUser);
        return "forward:/user/showMyProfile";
    }

    @RequestMapping("/showMyProfile")
    public String showInfo(HttpSession session, Model model) {
        User user = (User)session.getAttribute("user");
        try{
            user.setBirthdayStr(DateUtil.formateData(user.getBirthday()));
        } catch (NullPointerException e) {
            System.out.println("生日未设置");
        }
        model.addAttribute("user", user);
        return "before/my_profile";
    }

    @RequestMapping("/changeProfile")
    public String changeProfile(HttpSession session, Model model) {
        model.addAttribute("user", session.getAttribute("user"));
        return "before/change_profile";
    }

    @RequestMapping("/changeAvatar")
    public String changeAvatar(HttpSession session, Model model) {
        model.addAttribute("user", session.getAttribute("user"));
        return "before/change_avatar";
    }

    @RequestMapping("/passwordSafe")
    public String passwordSafe(HttpSession session, Model model) {
        model.addAttribute("user", session.getAttribute("user"));
        return "before/password_safe";
    }

    @RequestMapping("/validatePassword")
    @ResponseBody
    public int validatePassword(HttpSession session, String password) {
        String md5Password = MD5Utils.getMD5(password);
        User user = (User)session.getAttribute("user");
        if (md5Password.equals((user.getPassword()))) {
            return 1;
        } else {
            return 0;
        }
    }

    @RequestMapping("/updatePassword")
    public String updatePassword(String newPassword,String id, HttpSession session) {
        User user = new User(id, null, MD5Utils.getMD5(newPassword));
        userService.update(user);
        User newUser = userService.selectUser(user);
        session.setAttribute("user", newUser);
        return "forward:/user/showMyProfile";
    }

    @RequestMapping("/upLoadImage")
    public String upLoadHeadImage(@RequestParam MultipartFile image_file, HttpSession session, HttpServletRequest request){
        // 保存文件并获取文件的url
        String imgUrl = UploadFileUtil.uploadHeadImage(image_file, request);

        User sessionUser = (User)session.getAttribute("user");

        //创建一个对象封装参数
        User paraUser = new User();
        paraUser.setId(sessionUser.getId());
        paraUser.setImgUrl(imgUrl);

        userService.update(paraUser);

        //获取用户的最新信息
        User newUser = userService.selectUser(paraUser);
        // 更新session中用户的信息
        session.setAttribute("user", newUser);

        return "forward:/user/showMyProfile";
    }

    @RequestMapping("/sendEmail")
    @ResponseBody
    public void sendEmail(String email, HttpSession session) {
        // 产生一个随机的四位验证码
        String validateCode = ValidateCodeUtil.createValidateCode(4);
        session.setAttribute("emialValidateCode", validateCode);
        // 邮件标题
        String TITLE = "邮箱验证";
        // 邮件内容
        String CONTENT = "您的验证码为" + validateCode;
        try {
            JavaEmailSender.sendEmail(email, TITLE, CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/validateEmailCode")
    public String validateEmailCode(String email, String code, HttpSession session, Model model) {
        String validateCode = (String)session.getAttribute("emialValidateCode");
        if (code.equals(validateCode)) {
            session.removeAttribute("emialValidateCode");
            User paraUser = new User(null, email, null);
            User user = userService.selectUser(paraUser);
            model.addAttribute("user", user);
            return "before/reset_password";
        } else {
            model.addAttribute("errorInfo", "验证码错误");
            model.addAttribute("mail", email);
            return "before/forget_password";
        }
    }

    @RequestMapping("/resetPassword")
    public String resetPassword(User user, HttpSession session) {
        user.setPassword(MD5Utils.getMD5(user.getPassword()));
        userService.update(user);
        User newUser = userService.selectUser(user);
        session.setAttribute("user", newUser);
        return "before/index";
    }



}
