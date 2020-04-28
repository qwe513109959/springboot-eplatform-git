package com.edu.web.admin;

import com.edu.dao.mapper.IndexLoginDao;
import com.edu.pojo.User;
import com.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-26 18:25
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/tologinb")
    public String loginPageb() {
        return "admin/loginBootStrap";
    }

    @PostMapping("/loginb")
    public String loginb(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        // RedirectAttributes 专门用于重定向之后还能带参数跳转的的工具类
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            return "/admin/dashboard";
        } else {
            attributes.addFlashAttribute("msg", "用户名和密码不正确");
            return "redirect:/admin";
        }
    }

    @GetMapping("/tologin")
    public String loginPage() {
        return "admin/login";
    }


    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        // RedirectAttributes 专门用于重定向之后还能带参数跳转的的工具类
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("msg", "用户名和密码不正确");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

// ************* 对普通用户进行crud *************






}
