package com.edu.web.admin;

import com.edu.pojo.EnglishPlatform;
import com.edu.service.EduTypesService;
import com.edu.service.EplatformService;
import com.edu.service.GradeService;
import com.edu.vo.EplatformQuery;
import com.sun.org.apache.bcel.internal.generic.NEW;
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

    private static final String LIST= "admin/eplatform";

    @Autowired
    EplatformService eplatformService;
    @Autowired
    EduTypesService eduTypesService;
    @Autowired
    GradeService gradeService;


    /** 
    * @Description: 去EplatformList页面
    * @Param:  
    * @Author: Mr.Jia 
    * @Date: 2020/4/1 2:12 下午 
    */ 
    @RequestMapping(value = "/eplatform", method = RequestMethod.GET)
    public String toeplatform(@PageableDefault(size = 3, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                              EplatformQuery eplatform,
                              Model model) {
        model.addAttribute("types", eduTypesService.listEduType());
        model.addAttribute("page", eplatformService.listEplatform(pageable, eplatform));
        return LIST;
    }

    /** 
    * @Description: 复杂查询，名字+分类+推荐
    * @Param:  
    * @Author: Mr.Jia 
    * @Date: 2020/4/1 2:13 下午 
    */ 
    @RequestMapping(value = "/eplatform/search", method = RequestMethod.POST)
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         Model model,
                         EplatformQuery query){
        model.addAttribute("page", eplatformService.listEplatform(pageable, query));
        return "admin/eplatform :: eplatformList"; // 返回admin/eplatform 下的eplatformlist片段,只刷新列表
    }

    /** 
    * @Description: 去发布平台内容页面
    * @Param:  
    * @Author: Mr.Jia 
    * @Date: 2020/4/1 2:46 下午 
    */ 
    @GetMapping("/eplatform/input")
    public String input(Model model) {
        // model.addAttribute 往前台传数据，可以传对象，可以传List，通过el表达式 ${}可以获取到
        // 初始化grade,初始化的目的是，为了和edit共用页面的时候，去返回值到页面不会报错
        // 初始化分类type和等级grade
        model.addAttribute("types", eduTypesService.listEduType());
        model.addAttribute("grades", gradeService.listGrade());
        model.addAttribute("eplatform", new EnglishPlatform());
        return "admin/eplatform-input";
    }

    @GetMapping("/eplatform/inputtest")
    public String input() {
        return "admin/blogs-input";
    }
    /** 
    * @Description:
    * @Param:  
    * @Author: Mr.Jia 
    * @Date: 2020/4/1 2:49 下午 
    */ 

}
