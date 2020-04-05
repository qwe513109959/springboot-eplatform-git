package com.edu.dao;

import com.edu.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: spring-boot-eplatform
 * @description:
 * @author: Mr.jia
 * @date: 2020-04-05 17:06
 **/
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByEplatformIdAndParentCommentNull(Long eplatformId, Sort sort);

}
