package com.edu.dao;

import com.edu.pojo.EnglishPlatform;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-30 16:48
 **/
public interface EplatformRepository extends JpaRepository<EnglishPlatform, Long>, JpaSpecificationExecutor<EnglishPlatform>{

    @Query("select e from EnglishPlatform e where e.recommend = true")
    List<EnglishPlatform> findTop(Pageable pageable);

    // select * from tbl_eplatform where title like '%内容%'
    @Query("select e from EnglishPlatform e where e.title like ?1 or e.content like ?1")
    Page<EnglishPlatform> findByQuery(String query, Pageable pageable);

}

