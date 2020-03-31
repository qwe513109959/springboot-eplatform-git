package com.edu.dao;

import com.edu.pojo.EnglishPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-30 16:48
 **/
public interface EplatformRepository extends JpaRepository<EnglishPlatform, Long>, JpaSpecificationExecutor<EnglishPlatform>{

}
