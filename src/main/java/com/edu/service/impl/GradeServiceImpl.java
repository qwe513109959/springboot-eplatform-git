package com.edu.service.impl;

import com.edu.NotFoundException;
import com.edu.dao.GradeRepository;
import com.edu.pojo.Grade;
import com.edu.service.GradeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-31 19:10
 **/
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    GradeRepository repository;

    @Transactional
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

    @Override
    public List<Grade> listGrade() {
        return repository.findAll();
    }

    // 来获取一组grade对象的集合
    @Override
    public List<Grade> listGrade(String ids) { // 1,2,3
        return repository.findAllById(convertToList(ids));
    }

    @Override
    public List<Grade> listGradeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "englishPlatforms.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return repository.findTop(pageable);
    }

    // 数组转List
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null){
            String[] idsarray = ids.split(",");
            for (int i = 0; i < idsarray.length; i++) {
                list.add(new Long(idsarray[i]));
            }  
        }
        return list;
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
                throw new NotFoundException("不存在该等级");
            } catch (NotFoundException e) {
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
