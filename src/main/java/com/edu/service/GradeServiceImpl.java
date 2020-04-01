package com.edu.service;

import com.edu.NotFoundExcepiton;
import com.edu.dao.GradeRepository;
import com.edu.pojo.Grade;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-31 19:10
 **/
@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    GradeRepository repository;

    @Override
    public Grade getGrade(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Grade getGradeByName(String name) {
        return repository.findGradeByName(name);
    }

    @Transactional
    @Override
    public Page<Grade> listGrade(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    @Override
    public Grade saveGrade(Grade grade) {
        return repository.save(grade);
    }

    @Transactional
    @Override
    public Grade updateGrade(Long id, Grade grade) {
        Grade grade1 = repository.findById(id).get();
        if (grade1 == null) {
            try {
                throw new NotFoundExcepiton("不存在该等级");
            } catch (NotFoundExcepiton e) {
                e.printStackTrace();
            }
        }
        BeanUtils.copyProperties(grade, grade1);
        return null;
    }

    @Transactional
    @Override
    public void deleteGrade(Long id) {
        repository.deleteById(id);
    }
}