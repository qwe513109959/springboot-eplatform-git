package com.edu.dao;

import com.edu.pojo.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

/**
 * @program: spring-boot-eplatform-01
 * @description: 接口UserRepository继承jpa Repository 写自定义方法查询
 * @author: Mr.jia
 * @date: 2020-03-26 22:09
 **/
@Controller
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);
}
