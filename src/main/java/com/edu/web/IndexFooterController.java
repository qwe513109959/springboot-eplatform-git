package com.edu.web;

import com.edu.service.EplatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @program: spring-boot-eplatform
 * @description: about页面和footer页面重新加载
 * @author: Mr.jia4
 * @date: 2020-04-08 11:54
 **/
@Controller
public class IndexFooterController {

    @Autowired
    EplatformService eplatformService;

    @GetMapping("/footer/newList")
    public String aboutfooter(Model model){
        model.addAttribute("neweplatforms", eplatformService.listRecommendEplatformTop(3));
        return "_fragments :: neweplatformList";
    }


}
