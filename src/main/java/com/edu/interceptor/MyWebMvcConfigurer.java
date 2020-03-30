package com.edu.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @program: spring-boot-eplatform-01
 * @description: 注册自定义拦截器
 * @author: Mr.jia
 * @date: 2020-03-26 17:25
 **/
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

//        registry.addViewController("/tologin").setViewName("tologin");
        registry.addViewController("/register").setViewName("register");
        //添加url路径，设置url对应的页面
        //访问http://localhost:8080/toIndex，请求转发到http://localhost:8080/index这个页面
        registry.addViewController("/toIndex").setViewName("index");
        registry.addViewController("/header").setViewName("header");

         /*
        这个是重定向，比如访问http://localhost:8080，
        链接会重定向到http://localhost:8080/index，
        第一个参数是URL路径（你浏览器访问的地址），第二个是参数也是url路径，
        不过是上面配置好的url（就是上面addViewController里的玩意），
        它会访问映射的页面index.html(index.jsp,index.ftl)等等，前提是放在templates下的页面
        实际流程就是访问http://localhost:8080 ---> http://localhost:8080/toIndex --->
        ---> http://localhost:8080/index
        */
        // 默认访问路径 http://localhost:8080/
        registry.addRedirectViewController("/admin*","/admin/tologin");
    }

    // 加载不到static下的html
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/").addResourceLocations("/**");
    }



}


