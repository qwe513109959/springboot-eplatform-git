package com.edu.service;

import com.edu.pojo.EduTypes;
import com.edu.pojo.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @program: spring-boot-eplatform
 * @description: 前台登录接口
 * @author: Mr.jia
 * @date: 2020-04-24 14:28
 **/
public interface IndexLoginService {

    Student getStudentById(Integer id);

    Student findBySno(String sno);

    void saveStudent(Student student) ;

    void deleteStudent(Integer id);

    Student updateStudent(Integer id, Student student);

    Student findByUidAndPwd(Student student);

    Student findByEmail(String e_mail);

    List<Student> listStudents();

    Page<Student> listStudents(Pageable pageable);


}
