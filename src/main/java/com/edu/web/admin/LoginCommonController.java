package com.edu.web.admin;

import com.edu.pojo.User;
import com.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @program: spring-boot-eplatform
 * @description:
 * @author: Mr.jia
 * @date: 2020-04-10 16:34
 **/
@Controller
public class LoginCommonController {

    @Autowired
    private UserService userService;

    @GetMapping("/tologinb")
    public String loginPage() {
        return "admin/loginBootStrap";
    }

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        // RedirectAttributes 专门用于重定向之后还能带参数跳转的的工具类
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            System.out.println("用户：redirect:/main.html");
            return "/admin/dashboard";
        } else {
            attributes.addFlashAttribute("msg", "用户名和密码不正确");
            System.out.println("用户：loginBootStrap");
            return "redircet:loginBootStrap";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }


}
