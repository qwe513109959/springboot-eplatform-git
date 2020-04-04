package com.edu.dao;

import com.edu.pojo.EduTypes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-27 15:37
 **/
public interface EduTypesRepository extends JpaRepository<EduTypes, Long> {

    EduTypes findByName(String name);

    @Query("select e from EduTypes e")
    List<EduTypes> findTop(Pageable pageable);

}
