package com.edu.web;

import com.edu.pojo.EnglishPlatform;
import com.edu.pojo.Grade;
import com.edu.service.EplatformService;
import com.edu.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @program: spring-boot-eplatform
 * @description: 前端页面显示，级别；
 * @author: Mr.jia
 * @date: 2020-04-06 15:14
 **/
@Controller
public class GradeShowController {

    @Autowired
    GradeService gradeService;

    @Autowired
    EplatformService eplatformService;

    @GetMapping("/grade/{id}")
    public String showGrade(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                            @PathVariable Long id,
                            Model model) {
        List<Grade> grades = gradeService.listGradeTop(1000);
        if (id == -1) {
            id = grades.get(0).getId();
        }
        model.addAttribute("grades", grades);
        model.addAttribute("page", eplatformService.listEplatform(id, pageable));
        model.addAttribute("activeGradeId", id);
        return "grade";


    }

}
