package com.edu.web.admin;

import com.edu.web.Demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-29 20:43
 **/
@Controller
public class demo {

    @RequestMapping("/demoIndex")
    public String demo(Model model){
        model.addAttribute("demo",new Demo());
        return "/demo";
    }


    @PostMapping("/demoAdd")
    public String demoAdd(@Valid Demo demo,
                          BindingResult result,
                          Model model){
        //有错误信息.
        model.addAttribute("demo",demo);
        if(result.hasErrors()){
            result.rejectValue("name", "nameError", "不能重复输入");
            List<ObjectError> list = result.getAllErrors();
            for(ObjectError  error:list){
                System.out.println(error.getCode()+"---"+error.getArguments()+"---"+error.getDefaultMessage());
            }
            return "demo";
        }
        return "/demo";
    }
}