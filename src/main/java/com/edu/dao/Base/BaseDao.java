package com.edu.dao.Base;

import java.util.List;

/**
 * @program: spring-boot-eplatform
 * @description:
 * @author: Mr.jia
 * @date: 2020-04-24 14:36
 **/
public interface BaseDao<T> {

    public void save(T t);

    public void update(T t);

    public void delete(Integer id);

    public List<T> queryByList();

}
