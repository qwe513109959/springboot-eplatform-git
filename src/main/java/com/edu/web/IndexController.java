package com.edu.web;

import com.edu.pojo.EnglishPlatform;
import com.edu.service.EduTypesService;
import com.edu.service.EplatformService;
import com.edu.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: spring-boot-eplatform-01
 * @description: 首页展示
 * @author: Mr.jia
 * @date: 2020-04-03 15:49
 **/
@Controller
public class IndexController {

    @Autowired
    EplatformService eplatformService;

    @Autowired
    EduTypesService eduTypesService;

    @Autowired
    GradeService gradeService;

    @GetMapping("/index")
    public String toInde(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Model model) {
        model.addAttribute("page", eplatformService.listEplatform(pageable));
        model.addAttribute("types", eduTypesService.listEduTypeTop(6));
        model.addAttribute("grades", gradeService.listGradeTop(10));
        model.addAttribute("recommendEplatforms", eplatformService.listRecommendEplatformTop(8));
        return "/index";
    }

    @GetMapping("/eplatform/{id}")
    public String blog() {
        return "eplatform";
    }

}
