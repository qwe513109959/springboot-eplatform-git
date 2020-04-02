package com.edu.web.admin;

import com.edu.pojo.EnglishPlatform;
import com.edu.pojo.User;
import com.edu.service.EduTypesService;
import com.edu.service.EplatformService;
import com.edu.service.GradeService;
import com.edu.vo.EplatformQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


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
    @GetMapping("/eplatform")
    public String toeplatform(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                              EplatformQuery eplatform,
                              Model model) {
        model.addAttribute("types", eduTypesService.listEduType());
        model.addAttribute("eplatform", eduTypesService.listEduType());
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
    public String search(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
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

    // 测试test
    @GetMapping("/eplatform/inputtest")
    public String input() {
        return "admin/blogs-input";
    }

    /**
    * @Description: 新增数据
    * @Param:  
    * @Author: Mr.Jia 
    * @Date: 2020/4/1 2:49 下午 
    */ 
    @PostMapping("/eplatform")
    public String increatEplatform( EnglishPlatform eplatform,
                                    HttpSession session,
                                    RedirectAttributes attributes){
        eplatform.setUser((User) session.getAttribute("user"));
        eplatform.setEdu_type(eduTypesService.getEduType(eplatform.getEdu_type().getId()));
        eplatform.setGrade(gradeService.listGrade(eplatform.getGradeIds()));
        EnglishPlatform e;
        if (eplatform.getId() == null) {
            e = eplatformService.saveEplatform(eplatform);
        } else {
            e = eplatformService.updateEplatform(eplatform.getId(), eplatform);
        }

        if (e == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }

        return "redirect:/admin/eplatform";
    }

    /** 
    * @Description: 共用代码片段
    * @Param:  
    * @Author: Mr.Jia 
    * @Date: 2020/4/2 5:04 下午 
    */ 
    public void setTypeAndGrade(Model model) {
        model.addAttribute("types", eduTypesService.listEduType());
        model.addAttribute("grades", gradeService.listGrade());
    }

    /**
     * @Description: 编辑内容
     * @Param:
     * @Author: Mr.Jia
     * @Date: 2020/4/2 4:57 下午
     */
    @GetMapping("/eplatform/{id}/input")
    public String editInput(@PathVariable Long id,
                            RedirectAttributes attributes,
                            Model model) {
        setTypeAndGrade(model);
        EnglishPlatform eplatform = eplatformService.getEplatform(id);
        eplatform.init();
        model.addAttribute("eplatform", eplatform);
        return "admin/eplatform-input";
    }

    /**
    * @Description: 删除
    * @Param:
    * @Author: Mr.Jia
    * @Date: 2020/4/2 5:02 下午
    */
    @GetMapping("/eplatform/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        eplatformService.deleteEplatform(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/eplatform";
    }

}
