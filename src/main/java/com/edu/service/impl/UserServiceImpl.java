package com.edu.service.impl;

import com.edu.dao.mapper.UserMapper;
import com.edu.dao.UserRepository;
import com.edu.pojo.User;
import com.edu.service.UserService;
import com.edu.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-26 22:07
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

}
