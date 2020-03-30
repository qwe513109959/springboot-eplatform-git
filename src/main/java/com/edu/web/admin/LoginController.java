package com.edu.web.admin;

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
public class LoginController {

//    @PostMapping(value = "/user/login")
//    public String login(@RequestParam("username") String username,
//                        @RequestParam("password") String password,
//                        Map<String,Object> map, HttpSession session){
//        if(!StringUtils.isEmpty(username) && "123".equals(password)){
//            //登陆成功，防止表单重复提交，可以重定向到主页
//            session.setAttribute("loginUser",username);
//            return "redirect:/main.html";
//        }else{
//            //登陆失败
//            map.put("msg","用户名密码错误");
//            return  "login";
//        }
//    }

    @Autowired
    private UserService userService;

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
            session.setAttribute("loginUser", user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("msg", "用户名和密码不正确AAAAAA");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

    @GetMapping("/index")
    public String index(HttpSession session){
        return "admin/index";
    }

}
