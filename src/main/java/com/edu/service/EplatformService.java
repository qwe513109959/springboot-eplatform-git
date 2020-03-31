package com.edu.service;

import com.edu.pojo.EnglishPlatform;
import com.edu.vo.EplatformQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-30 16:34
 **/
public interface EplatformService {

    EnglishPlatform getEplatform(Long id);

    Page<EnglishPlatform> listEplatform(Pageable pageable, EplatformQuery query);

    EnglishPlatform saveEplatform(EnglishPlatform englishPlatform);

    EnglishPlatform updateEplatform(Long id, EnglishPlatform englishPlatform);

    void deleteEplatform(Long id);


}
