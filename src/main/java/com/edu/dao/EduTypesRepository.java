package com.edu.dao;

import com.edu.pojo.EduTypes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-27 15:37
 **/
public interface EduTypesRepository extends JpaRepository<EduTypes, Long> {

    EduTypes findByName(String name);

}
