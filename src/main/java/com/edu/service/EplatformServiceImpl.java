package com.edu.service;

import com.edu.NotFoundException;
import com.edu.dao.EplatformRepository;
import com.edu.pojo.EduTypes;
import com.edu.pojo.EnglishPlatform;
import com.edu.util.MarkdownUtils;
import com.edu.util.MyBeanUtils;
import com.edu.vo.EplatformQuery;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.hibernate.query.criteria.internal.predicate.BooleanAssertionPredicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
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
     * @Description: Markdown转HTML，详情页面
     * @Param:
     * @Author: Mr.Jia
     * @Date: 2020/4/5 12:06 下午
     */
    @Transactional
    @Override
    public EnglishPlatform getAndConvert(Long id) {
        EnglishPlatform eplatform = repository.getOne(id);
        if (eplatform == null) {
            throw new NotFoundException("该内容不存在getAndConvert");
        }
        EnglishPlatform eplatform1 = new EnglishPlatform();
        // 将source赋值给target，保证源对象不会修改数据库的内容。将新对象改变内容传到详情页面
        BeanUtils.copyProperties(eplatform, eplatform1);
        String content = eplatform1.getContent();
        eplatform1.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        // 更新查看次数Views+1
        repository.updateViews(id);
        return eplatform1;
    }

    /**
     * @Description: 高级查询，组合查询
     * @Param:  list
     * @Author: Mr.Jia
     * @Date: 2020/3/30 5:01 下午
     */
    @Transactional
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
                // getType() 报空指针异常
                // if (query.getType().getId() != null) {   Eplatform >> EplatformQuery
                if (query.getTypeId() != null) {
                    predicates.add(cb.equal(root.<EduTypes>get("id"), query.getTypeId()));
                }
                if (query.isRecommend()) {
                    predicates.add(cb.equal(root.<BooleanAssertionPredicate>get("recommend"), query.isRecommend()));
                }
                // cq进行查询
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    
    /** 
    * @Description: String类型的query + 分页查询
    * @Param:  
    * @Author: Mr.Jia 
    * @Date: 2020/4/4 7:37 下午 
    */ 
    @Override
    public Page<EnglishPlatform> listEplatform(String query, Pageable pageable) {
        return repository.findByQuery(query, pageable);
    }

    /** 
    * @Description: 分页查询参数
    * @Param:  
    * @Author: Mr.Jia 
    * @Date: 2020/4/4 6:20 下午 
    */ 
    @Override
    public Page<EnglishPlatform> listEplatform(Pageable pageable) {
        return repository.findAll(pageable);
    }
    
    /** 
    * @Description: Jpa；按更新时间排序
    * @Param:  
    * @Author: Mr.Jia 
    * @Date: 2020/4/4 6:16 下午 
    */ 
    @Override
    public List<EnglishPlatform> listRecommendEplatformTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return repository.findTop(pageable);
    }

    @Transactional
    @Override
    public EnglishPlatform saveEplatform(EnglishPlatform e) {
        if (e.getId() == null) {
            // 设置默认值：阅览0次
            e.setCreateTime(new Date());
            e.setUpdateTime(new Date());
            e.setViews(0);
        } else {
            e.setUpdateTime(new Date());
        }
        return repository.save(e);
    }

    @Transactional
    @Override
    public EnglishPlatform updateEplatform(Long id, EnglishPlatform englishPlatform) {
        EnglishPlatform e = repository.findById(id).get();
        if (e == null) {
            throw new NotFoundException("该平台内容不存在");
        }
        // 把传过来的englishPlatform的值 赋值给已有的e对象
        // 过滤掉属性值为空的属性，只copy englishPlatform里有值的属性 到e
        // copyProperties(数据源，赋值对象)
        BeanUtils.copyProperties(englishPlatform, e, MyBeanUtils.getNullPropertyNames(englishPlatform));
        e.setUpdateTime(new Date());
        return repository.save(e);
    }

    @Transactional
    @Override
    public void deleteEplatform(Long id) {
        repository.deleteById(id);
    }
}
