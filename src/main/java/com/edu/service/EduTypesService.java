package com.edu.service;

import com.edu.pojo.EduTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-27 15:26
 **/
public interface EduTypesService {

    EduTypes saveEduType(EduTypes type);

    EduTypes getEduType(Long id);

    EduTypes getEduTypeByName(String name);

    Page<EduTypes> listEduType(Pageable pageable);

    EduTypes updateEduType(Long id, EduTypes type);

    void deleteEduType(Long id);

}
