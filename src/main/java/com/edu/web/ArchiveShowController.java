package com.edu.web;

import com.edu.service.EplatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: spring-boot-eplatform
 * @description: 归档
 * @author: Mr.jia
 * @date: 2020-04-07 21:10
 **/
@Controller
public class ArchiveShowController {

    @Autowired
    EplatformService eplatformService;

    @GetMapping("/archive")
    public String show(Model model){
        model.addAttribute("archiveMap", eplatformService.archiveEplatform());
        model.addAttribute("EplatformCount", eplatformService.countEplatform());
        return "archives";
    }

}
