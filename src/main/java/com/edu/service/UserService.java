package com.edu.service;

import com.edu.pojo.User;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-26 22:06
 **/

public interface UserService {

   public User checkUser(String username, String password);

   public User getUserById(Long id);
}
