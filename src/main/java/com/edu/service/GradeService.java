package com.edu.service;

import com.edu.pojo.EduTypes;
import com.edu.pojo.EnglishPlatform;
import com.edu.pojo.Grade;
import com.edu.vo.EplatformQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-31 19:07
 **/
public interface GradeService {

    Grade getGrade(Long id);

    Grade getGradeByName(String name);

    Page<Grade> listGrade(Pageable pageable);

    Grade saveGrade(Grade grade);

    Grade updateGrade(Long id, Grade grade);

    void deleteGrade(Long id);

}
