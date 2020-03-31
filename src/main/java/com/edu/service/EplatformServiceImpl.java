package com.edu.service;

import com.edu.NotFoundExcepiton;
import com.edu.dao.EplatformRepository;
import com.edu.pojo.EduTypes;
import com.edu.pojo.EnglishPlatform;
import com.edu.vo.EplatformQuery;
import org.hibernate.query.criteria.internal.predicate.BooleanAssertionPredicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: spring-boot-eplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-30 16:46
 **/
@Service
public class EplatformServiceImpl implements EplatformService {

    @Autowired
    EplatformRepository repository;

    @Override
    public EnglishPlatform getEplatform(Long id) {
        return repository.getOne(id);
    }

    /**
     * @Description: 高级查询，组合查询
     * @Param:
     * @Author: Mr.Jia
     * @Date: 2020/3/30 5:01 下午
     */
    @Override
    public Page<EnglishPlatform> listEplatform(Pageable pageable, EplatformQuery query) {
        //规格定义
        return repository.findAll(new Specification<EnglishPlatform>() {
            /**
             * @Description: 构造断言
             * @param root 实体对象引用
             * @param cq 规则查询对象
             * @param cb 规则构建对象
             * @return 断言
             */
            @Override
            public Predicate toPredicate(Root<EnglishPlatform> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //所有的断言
                List<Predicate> predicates = new ArrayList<>();
                //添加断言
                if (!"".equals(query.getTitle()) && query.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%" + query.getTitle() + "%"));
                }
                if (query.getTypeId() != null) {
                    predicates.add(cb.equal(root.<EduTypes>get("id"), query.getTypeId()));
                }
                if (query.isRecommend()) {
                    predicates.add(cb.equal(root.<BooleanAssertionPredicate>get("reconmmend"), query.isRecommend()));
                }
                // cq进行查询
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public EnglishPlatform saveEplatform(EnglishPlatform englishPlatform) {
        return repository.save(englishPlatform);
    }

    @Override
    public EnglishPlatform updateEplatform(Long id, EnglishPlatform englishPlatform) {
        EnglishPlatform one = repository.findById(id).get();
        if (one == null) {
            throw new NotFoundExcepiton("该平台内容不存在");
        }
        BeanUtils.copyProperties(englishPlatform, one);
        return repository.save(one);
    }

    @Override
    public void deleteEplatform(Long id) {
        repository.deleteById(id);
    }
}
