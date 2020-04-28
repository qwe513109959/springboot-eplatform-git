package com.edu.dao.mapper;

import com.edu.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: spring-boot-eplatform
 * @description:
 * @author: Mr.jia
 * @date: 2020-04-24 14:34
 **/
@Mapper
@Component
public interface IndexLoginDao {

    @Select("select * from tbl_student where id=#{id}")
    public Student getById(Integer id);

    @Delete("delete from tbl_student where id=#{id}")
    public int deleteById(Integer id);

//    @Options(useGeneratedKeys = true,keyProperty = "id")
//    @Insert("insert into department(nickname,sno,password,email,avatar,type,create_time,update_time) " +
//            "values(#{nickname},#{sno},#{password},#{email},#{avatar},#{type},#{create_time},#{update_time})")
//    public int insert(Student student);

    @Update("update tbl_student set nickname=#{nickname},sno=#{sno},password=#{password},email=#{email},avatar=#{avatar},type=#{type},create_time=#{createTime},update_time=#{updateTime} where id=#{id}")
    public int updateStudent(Student student);

    @Select("select * from tbl_student")
    List<Student> getAllStudents();

    /*

    Student getStudentById(Integer id);

    Student findBySno(String sno);

    void saveStudent(Student student) ;

    void deleteStudent(Integer id);

    Student updateStudent(Integer id, Student student);

    Student findByUidAndPwd(Student student);

    Student findByEmail(String e_mail);

    List<Student> listStudents();

    Page<Student> listStudents(Pageable pageable);
     */


    /**
     * 通过学号和密码查询
     * @param student
     * @return
     */
    public Student queryByUidAndPwd(Student student);

    /**
     * 通过学号
     * @param sno
     * @return student
     */
    public Student queryBySno(String sno);

    /**
     * 通过邮箱查询
     * @param email
     * @return student
     */
    public Student queryByEmail(String email);

    /**
     * 添加学生
     * @param student
     * @return void
     */
    public void saveStudent(Student student);
}
