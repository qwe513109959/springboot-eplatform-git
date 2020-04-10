package com.edu.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/tologinb")
    public String loginPage() {
        return "admin/loginBootStrap";
    }

    @PostMapping(value = "/user/login")
//    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //登陆成功，防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser", username);
            System.out.println("用户：redirect:/main.html");
            return "redirect:/main.html";
        } else {
            //登陆失败
            map.put("msg", "用户名密码错误");
            System.out.println("用户：loginBootStrap");
            return "loginBootStrap";
        }

    }

}
