package com.edu.service;

import com.edu.NotFoundException;
import com.edu.dao.EduTypesRepository;
import com.edu.pojo.EduTypes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-27 15:26
 **/
@Service
public class EduTypesServiceImpl implements EduTypesService {

    @Autowired
    private EduTypesRepository repository;

    @Transactional
    @Override
    public EduTypes saveEduType(EduTypes type) {
        return repository.save(type);
    }

    @Override
    public EduTypes getEduType(Long id) {
        return repository.getOne(id);
    }

    @Override
    public EduTypes getEduTypeByName(String name) {
        return repository.findByName(name);
    }

    @Transactional
    @Override
    public Page<EduTypes> listEduType(Pageable pageable) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
//        Pageable pageable = PageRequest.of(10, 0, sort);
        return repository.findAll(pageable);
    }

    @Override
    public List<EduTypes> listEduType() {
        return repository.findAll();
    }

    /**
     * @Description: Jpa实现Sort排序
     * @Param:
     * @Author: Mr.Jia
     * @Date: 2020/4/3 4:50 下午
     */
    @Transactional
    @Override
    public List<EduTypes> listEduTypeTop(Integer size) {
        // properties：bean类中的字段
        Sort sort = Sort.by(Sort.Direction.DESC,"englishPlatforms.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return repository.findTop(pageable);
    }

    @Transactional
    @Override
    public EduTypes updateEduType(Long id, EduTypes type) {
        EduTypes eduTypes = repository.findById(id).get();
        if (eduTypes == null) {
            try {
                throw new NotFoundException("不存在该类型");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        BeanUtils.copyProperties(type,eduTypes);
        return repository.save(eduTypes);
    }

    @Transactional
    @Override
    public void deleteEduType(Long id) {
        repository.deleteById(id);
    }
}
