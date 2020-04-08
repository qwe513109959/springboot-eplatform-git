package com.edu.web;

import com.edu.pojo.EduTypes;
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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @program: spring-boot-eplatform
 * @description: 前端分类页面显示
 * @author: Mr.jia
 * @date: 2020-04-05 21:13
 **/
@Controller
public class IndexEduTypeController {

    @Autowired
    EduTypesService eduTypesService;

    @Autowired
    EplatformService eplatformService;

    @GetMapping("/types/{id}")
    public String showEduTypes(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                               @PathVariable Long id,
                               Model model) {
        List<EduTypes> types = eduTypesService.listEduTypeTop(10000);
        if (id == -1) { // 获得type集合中的第一个元素的id
            id = types.get(0).getId();
        }
        EplatformQuery query = new EplatformQuery();
        query.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page", eplatformService.listEplatform(pageable, query));
        model.addAttribute("activeEduTypeId", id);
        return "edutypes";
    }

}
