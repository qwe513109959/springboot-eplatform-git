package com.edu.dao;

import com.edu.pojo.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-31 19:11
 **/
public interface GradeRepository  extends JpaRepository<Grade,Long> {

    Grade findGradeByName(String name);
}
