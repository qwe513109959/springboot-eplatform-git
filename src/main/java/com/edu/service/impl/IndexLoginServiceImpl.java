package com.edu.service.impl;

import com.edu.dao.mapper.IndexLoginDao;
import com.edu.pojo.Student;
import com.edu.service.IndexLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: spring-boot-eplatform
 * @description: 前台登录接口具体实现
 * @author: Mr.jia
 * @date: 2020-04-24 14:39
 **/
@Service
public class IndexLoginServiceImpl implements IndexLoginService {

    @Autowired
    IndexLoginDao indexlogindao;

    /**
     * @Title: findByUidAndPwd
     * @Description: 根据id和Password来查找Student
     * @param student
     * @return
     */
    public Student findByUidAndPwd(Student student) {
        return indexlogindao.queryByUidAndPwd(student);
    }

    /** 
    * @Description: 根据id查询学生
    * @Param: 
    * @Author: Mr.Jia 
    * @Date: 2020/4/25 3:00 下午
    */ 
    @Override
    public Student getStudentById(Integer id) {
        return indexlogindao.getById(id);
    }

    /**
     * @Title: findByUid
     * @Description: 根据Sno来查找Student
     * @param sno
     * @return student
     */
    public Student findBySno(String sno) {
        return indexlogindao.queryBySno(sno);
    }

    /**
     * @Title: findByEmail
     * @Description: 根据E_mail来查找Student
     * @param e_mail
     * @return student
     */
    public Student findByEmail(String e_mail) {
        return indexlogindao.queryByEmail(e_mail);
    }

    @Override
    public List<Student> listStudents() {
        List<Student> list =  indexlogindao.getAllStudents();
        return list;
    }

    @Override
    public Page<Student> listStudents(Pageable pageable) {
        return null;
    }

    /**
     * 添加学生
     * @param student
     */
    public void saveStudent(Student student)  {
        indexlogindao.saveStudent(student);
    }

    @Override
    public void deleteStudent(Integer id) {

    }

    @Override
    public Student updateStudent(Integer id, Student student) {
        return null;
    }
}
