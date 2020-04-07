package com.edu.service;

import com.edu.pojo.EnglishPlatform;
import com.edu.vo.EplatformQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-30 16:34
 **/
public interface EplatformService {

    EnglishPlatform getEplatform(Long id);

    EnglishPlatform getAndConvert(Long id);

    Page<EnglishPlatform> listEplatform(Pageable pageable, EplatformQuery query);

    Page<EnglishPlatform> listEplatform(Long gradeId, Pageable pageable);

    /**
     * 获取年份分组
     * 拿到该年份所对应的列表
     */
    Map<String, List<EnglishPlatform>> archiveEplatform();

    Long countEplatform();

    // String 类型的 query
    Page<EnglishPlatform> listEplatform(String query, Pageable pageable);

    Page<EnglishPlatform> listEplatform(Pageable pageable);

    List<EnglishPlatform> listRecommendEplatformTop(Integer size);

    EnglishPlatform saveEplatform(EnglishPlatform englishPlatform);

    EnglishPlatform updateEplatform(Long id, EnglishPlatform englishPlatform);

    void deleteEplatform(Long id);


}
