package com.edu.web.admin;

import com.edu.dao.mapper.IndexLoginDao;
import com.edu.pojo.Student;
import com.edu.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: spring-boot-eplatform
 * @description: 学生类操作
 * @author: Mr.jia
 * @date: 2020-04-25 15:40
 **/
@Controller
@RequestMapping("/admin")
public class StudentController {

    @Autowired
    IndexLoginDao studentDao;

    //查询所有student返回列表页面
    @GetMapping("/students")
    public String list(Model model){
        List<Student> students = studentDao.getAllStudents();
        //放在请求域中
        model.addAttribute("students",students);
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "student/list";
    }

    //来到student添加页面
    @GetMapping("/toaddstudent")
    public String toAddPage(Model model){
        return "student/add";
    }

    // 学生添加
    // SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @RequestMapping(value = "/student",method = RequestMethod.POST)
    public String addStudent(Student student,@RequestParam(value="password") String password){
        //来到 学生列表页面
        System.out.println("保存的student信息："+student);
        //保存student
        student.setPassword(MD5Utils.code(password));
//        String strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        student.setType(2);
        student.setCreateTime(new Date());
        student.setUpdateTime(new Date());
        studentDao.saveStudent(student);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/admin/students";
    }

    // 来到修改页面，查出当前学生，在页面回显
    @GetMapping("/editstudent/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Student student = studentDao.getById(id);
        model.addAttribute("student", student);
        //回到修改页面(add是一个修改添加二合一的页面);
        return "student/add";
    }

    //  学生修改；需要提交 学生id；
    @PostMapping("/editstudent")
    public String updateStudent(Student student,
                                @RequestParam(value="password") String password,
                                @RequestParam(value="create_time") Date createTime) {
        System.out.println("修改的学生数据：" + student);
        student.setPassword(MD5Utils.code(password));
        student.setType(2);
        student.setCreateTime(createTime);
        student.setUpdateTime(new Date());
        studentDao.updateStudent(student);
        return "redirect:/admin/students";
    }

    // 学生删除
    @RequestMapping("/student/{id}")
    public String deleteStudent(@PathVariable("id") Integer id){
        System.out.println("删除的学生数据" + id);
        studentDao.deleteById(id);
        return "redirect:/admin/students";
    }

}
