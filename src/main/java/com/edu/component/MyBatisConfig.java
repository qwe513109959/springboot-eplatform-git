package com.edu.component;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @program: spring-boot-eplatform
 * @description: 自定义MyBatis的配置规则；给容器中添加一个ConfigurationCustomizer；
 * @author: Mr.jia
 * @date: 2020-04-24 10:08
 **/
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {

            @Override
            public void customize(Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}