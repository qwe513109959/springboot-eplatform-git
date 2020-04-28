package com.edu.web;

import com.edu.pojo.Student;
import com.edu.pojo.User;
import com.edu.service.EplatformService;
import com.edu.service.IndexLoginService;
import com.edu.service.UserService;
import com.edu.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @program: spring-boot-eplatform
 * @description: 前端登录页面
 * @author: Mr.jia
 * @date: 2020-04-08 22:11
 **/
@Controller
public class IndexLoginController {

    @Autowired(required = false)
    private IndexLoginService indexloginservice;

    @Autowired
    EplatformService eplatformService;

    @GetMapping("/toindexlogin")
    public String loginPage() {
        return "/login";
    }

    /**
     * @Description: 登录成功到index页面，失败返回到/login页面
     * @Param:
     * @Author: Mr.Jia
     * @Date: 2020/4/24 3:19 下午
     */
    @RequestMapping(value = "/indexlogin", method = RequestMethod.POST)
    public String login(@RequestParam("sno") String sno,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        // RedirectAttributes 专门用于重定向之后还能带参数跳转的的工具类
        Student student = new Student();
        student.setSno(sno);
        student.setPassword(MD5Utils.code(password));
        student = indexloginservice.findByUidAndPwd(student);
        if (student != null) {
            student.setPassword(null);
            session.setAttribute("student", student);
            return "redirect:/index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码不正确");
            return "redirect:/toindexlogin";
        }
    }

    /** 
    * @Description: 注销页面
    * @Param: 
    * @Author: Mr.Jia 
    * @Date: 2020/4/24 3:19 下午 
    */ 
    @GetMapping("/indexlogout")
    public String logout(HttpSession session){
        session.removeAttribute("student");
        return "redirect:/index";
    }
    
    /**
     * @Description: 去注册页面
     * @Param:
     * @Author: Mr.Jia
     * @Date: 2020/4/24 10:46 上午
     */
    @RequestMapping("/toregister")
    public String toRegister() {
        return "register";
    }

    /**
     *
     * @Title: checkSno
     * @Description: 检查用户名是否存在
     * @param
     * @return:
     */
    @RequestMapping("/checkSno")
    @ResponseBody
    public void checkSno(@RequestParam(value="sno") String sno, HttpServletRequest request,
                         HttpServletResponse response) throws Exception{
        Student student = new Student();
        student = indexloginservice.findBySno(sno);
        boolean isExist = true;
        if(student == null){
            isExist = false;
        }
        response.getWriter().write("{\"isExist\":"+isExist+"}");
    }

    /**
     *
     * @Title: checkEmail
     * @Description: 检查邮箱是否被注册过
     * @param
     * @return:
     * @throws Exception
     */
    @RequestMapping("/checkEmail")
    @ResponseBody
    public void checkEmail(@RequestParam(value="e_mail") String e_mail,HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        Student student = new Student();
        student = indexloginservice.findByEmail(e_mail);
        boolean isExist = true;
        if(student == null){
            isExist = false;
        }
        response.getWriter().write("{\"isExist\":"+isExist+"}");
    }

    /**
     *
     * @Title: addStudent
     * 添加注册用户
     * @throws Exception
     */
    @RequestMapping("/addStudent")
    public ModelAndView addStudent(@RequestParam(value="nickname") String nickname,
                                   @RequestParam(value="sno") String sno,
                                   @RequestParam(value="password") String password,
                                   @RequestParam(value="repassword") String repassword,
                                   @RequestParam(value="email") String email,
                                   @RequestParam(value="avatar") String avatar,
                                   @RequestParam(value="type") Integer type,
                                   @RequestParam(value="create_time") String create_time,
                                   @RequestParam(value="update_time") String update_time) {
        Student student = new Student();
        ModelAndView mv = new ModelAndView();
        student.setSno(sno);
        student.setNickname(nickname);
        student.setSno(sno);
        student.setPassword(MD5Utils.code(password));
        student.setEmail(email);
        student.setAvatar(avatar);
        student.setType(2);
        student.setCreateTime(new Date());
        student.setUpdateTime(new Date());

        // if(student.getSno()!=""&&student.getNickname()!=""&&student.getPassword()!=""&&student.getEmail()!=""&&repassword.equals(password)){
        if(student.getSno()!=""){
            indexloginservice.saveStudent(student);
            mv.setViewName("/login");
        }else{
            mv.setViewName("/register");
            System.out.println("一个提示");
        }
        return mv;
    }

    /**
     *
     * 去忘记密码页面
     * @return: ModelAndView
     * @throws Exception
     */

    /*
    @RequestMapping("/to_forget")
    public ModelAndView toForgotpass() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home/forgetpass");
        return mv;
    }
    */


    /**
     * 忘记密码
     *
     * @param e_mail
     * @return
     * @throws Exception
     */

    /*
    @RequestMapping("/forgotpass")
    public ModelAndView ForgotPass(@RequestParam(value="e_mail") String e_mail) throws Exception  {
        ModelAndView mv = new ModelAndView();
        Student student = new Student();
        student = indexloginservice.findByEmail(e_mail);
        if(student != null){
            ForgetMail.sendEmail(e_mail);
            mv.setViewName("home/login");
        }
        else{
            mv.setViewName("home/forgetpass");
        }

        return mv;
    }
    */



}
