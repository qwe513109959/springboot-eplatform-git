package com.edu.web.admin;

import com.edu.service.EduTypesService;
import com.edu.service.EplatformService;
import com.edu.vo.EplatformQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @program: spring-boot-eplatform-01
 * @description: 平台内容列表
 * @author: Mr.jia
 * @date: 2020-03-30 16:33
 **/
@Controller
@RequestMapping("/admin")
public class EplatformController {

    @Autowired
    EplatformService eplatformService;

    @Autowired
    EduTypesService eduTypesService;

    @RequestMapping(value = "/eplatform", method = RequestMethod.GET)
    public String toeplatform(@PageableDefault(size = 3, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                              EplatformQuery eplatform,
                              Model model) {
        model.addAttribute("types", eduTypesService.listEduType());
        model.addAttribute("page", eplatformService.listEplatform(pageable, eplatform));
        return "admin/eplatform";
    }

    @RequestMapping(value = "/eplatform/search", method = RequestMethod.GET)
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Model model,
                         EplatformQuery query){
        model.addAttribute("page", eplatformService.listEplatform(pageable, query));
        return "admin/eplatform :: eplatformList";
    }

}
