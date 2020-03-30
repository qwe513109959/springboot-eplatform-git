package com.edu.web.admin;

import com.edu.pojo.EduTypes;

import com.edu.service.EduTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @program: spring-boot-eplatform-01
 * @description:  分类
 * @author: Mr.jia
 * @date: 2020-03-27 15:25
 **/
@Controller
@RequestMapping("/admin")
public class EduTypesController {

    @Autowired
    EduTypesService eduTypeService;

    /**
     * @Description: 去list页面
     * @Param:
     * @Author: Mr.Jia
     * @Date: 2020-03-27 21:19:00
     */
    @GetMapping("/edutypes")
    public String types(@PageableDefault(size = 3, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                        HttpSession session, Model model){
        // @tlT(size = 3,sort = {"id"},direction = Sort.Direction.DESC)
        //                  Pageable pageable, Model model)  {
        model.addAttribute("page", eduTypeService.listEduType(pageable));
        return "admin/edutypes";
    }

    /**
     * @Description: 去add页面
     * @Param:
     * @Author: Mr.Jia
     * @Date:   2020-03-27 21:19:12
     */
    @RequestMapping(value = "/edutypes/input", method=RequestMethod.GET)
    public String toinput(Model model) {
        // 和th:object="${edutypes}"搭配使用
        // th:if="${#fields.hasErrors('name')}" 没啥用啊
        model.addAttribute("type", new EduTypes());
        return "admin/AAedutypes-input";
    }

    /**
     * @Description: 新增数据
     * @Param:
     * @Author: Mr.Jia
     * @Date:   2020-03-27 21:29:11
     */
    @PostMapping("/edutypes")
    public String postTypes(@Valid EduTypes eduTypes,
                            BindingResult result,
                            RedirectAttributes attributes,
                            Model model){
        model.addAttribute("type",eduTypes);
        EduTypes eduTypes1 = eduTypeService.getEduTypeByName(eduTypes.getName());
        if (eduTypes1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
            return "admin/AAedutypes-input";
        }
        if (result.hasErrors()) {
            return "admin/AAedutypes-input";
        }
        EduTypes eduTypes2 = eduTypeService.saveEduType(eduTypes);
        if (eduTypes2 == null ) {
            // 给edutypes传递message
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/AAedutypes";
    }


    /**
     * @Description:  去编辑页面
     * @Param:
     * @Author: Mr.Jia
     * @Date: 2020-03-27 21:29:05
     */
    @GetMapping("/edutypes/{id}/input")
    public String toeditInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", eduTypeService.getEduType(id));
        return "admin/edutypes-input";
    }

    /**
     * @Description: 编辑信息
     * @Param:
     * @Author: Mr.Jia
     * @Date:   2020-03-27 21:29:02
     */
    @PostMapping("/edutypes/{id}")
    public String editPost(@Valid EduTypes eduTypes,
                           BindingResult result,
                           @PathVariable Long id,
                           RedirectAttributes attributes) {
        EduTypes eduTypes1 = eduTypeService.getEduTypeByName(eduTypes.getName());
        if (eduTypes1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/edutypes-input";
        }
        EduTypes eduTypes2 = eduTypeService.updateEduType(id, eduTypes);
        if (eduTypes2 == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/edutypes";
    }

    /**
     * @Description: 删除信息
     * @Param:
     * @Author: Mr.Jia
     * @Date: 2020-03-27 21:28:57
     */
    @GetMapping("/edutypes/{id}/delete")
    public String deleteType(@PathVariable Long id, RedirectAttributes attributes){
        eduTypeService.deleteEduType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/edutypes";
    }

}