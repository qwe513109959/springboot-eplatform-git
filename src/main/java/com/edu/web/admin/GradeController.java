package com.edu.web.admin;

import com.edu.pojo.EduTypes;
import com.edu.pojo.Grade;
import com.edu.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.management.VMOptionCompositeData;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-31 19:03
 **/
@Controller
@RequestMapping(value = "/admin")
public class GradeController {

    @Autowired
    GradeService gradeService;

    /**
     * @Description: 去gradeList页面
     * @Param:
     * @Author: Mr.Jia
     * @Date: 2020-03-27 21:19:00
     */
    @GetMapping("/grade")
    public String toGradeList(@PageableDefault(size = 3, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                              Model model,
                              HttpSession session) {
        model.addAttribute("page", gradeService.listGrade(pageable));
        return "admin/grade";
    }
    
    /** 
    * @Description: 去add页面
    * @Param:  
    * @Author: Mr.Jia 
    * @Date: 2020/3/31 7:30 下午 
    */
    @GetMapping("/grade/input")
    public String toAddGrade(Model model){
        model.addAttribute("grade", new Grade());
        return "/admin/grade-input";
    }

    /**
     * @Description: 新增grade
     * @Param:
     * @Author: Mr.Jia
     * @Date: 2020/3/31 7:29 下午
     */
    @PostMapping("/grade")
    public String addGrade(@Valid Grade grade,
                           BindingResult result,
                           RedirectAttributes attributes,
                           Model model) {
        model.addAttribute("grade", grade);
        Grade grade1 = gradeService.getGradeByName(grade.getName());
        if (grade1 != null) {
            result.rejectValue("name", "nameError", "不能添加重复的等级");
            return "admin/grade-input";
        }
        if (result.hasErrors()) {
            return "admin/grade-input";
        }
        Grade grade2 = gradeService.saveGrade(grade);
        if (grade2 == null) {
            // 给grade传递message
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/grade";
    }

    /**
     * @Description: 去编辑页面
     * @Param:
     * @Author: Mr.Jia
     * @Date: 2020/3/31 7:29 下午
     */
    @GetMapping("/grade/{id}/input")
    public String toedit(@PathVariable Long id, Model model){
        model.addAttribute("grade", gradeService.getGrade(id));
        return "admin/grade-input";
    }

    /**
     * @Description: 编辑grade
     * @Param:
     * @Author: Mr.Jia
     * @Date: 2020/3/31 7:29 下午
     */
    @PostMapping("/grade/{id}")
    public String editGrade(@Valid Grade grade,
                            BindingResult result,
                            RedirectAttributes attributes,
                            @PathVariable Long id,
                            Model model) {
        model.addAttribute("grade", grade);
        Grade grade1 = gradeService.getGradeByName(grade.getName());
        if (grade1 != null) {
            result.rejectValue("name", "nameError", "不能添加重复等级");
            return "admin/grade-input";
        }
        if (result.hasErrors()) {
            return "admin/grade-input";
        }
        Grade grade2 = gradeService.updateGrade(id, grade);
        if (grade2 != null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/grade";
    }


    /**
     * @Description: 删除grade
     * @Param:
     * @Author: Mr.Jia
     * @Date: 2020/3/31 7:29 下午
     */
    @GetMapping("/grade/{id}/delete")
    public String deleteGrade(@PathVariable("id") Long id,
                              RedirectAttributes attributes) {
        gradeService.deleteGrade(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/grade";
    }

}
