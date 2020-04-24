package com.edu.dao.mapper;

import com.edu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @program: spring-boot-eplatform
 * @description:
 * @author: Mr.jia
 * @date: 2020-04-23 22:00
 **/
@Mapper
@Component
public interface UserMapper {

    public User getUserById(Long id);

//    public void insertUser(User user);

//    public void deleteUser(Long id);

//    public void editUser(User user);

}
