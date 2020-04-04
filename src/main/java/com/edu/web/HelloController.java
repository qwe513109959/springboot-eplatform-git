package com.edu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    @GetMapping("/helloIndex")
    public String index(){
        //int i = 9 / 0;
        return "index";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "helloooo";
    }

    // 查出用户数据，在页面展示
    @RequestMapping("/toIndex")
    public String toIndex(Map<String, Object> map) {
        map.put("hello", "<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu"));
        return "index";
    }

    // 查出用户数据，在页面展示
    @RequestMapping("/toss")
    public String toss(Map<String, Object> map) {
        map.put("hello", "<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu"));
        return "ss";
    }




}
