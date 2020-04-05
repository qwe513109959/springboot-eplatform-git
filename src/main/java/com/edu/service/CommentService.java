package com.edu.service;

import com.edu.pojo.Comment;

import java.util.List;

/**
 * @program: spring-boot-eplatform
 * @description:
 * @author: Mr.jia
 * @date: 2020-04-05 17:04
 **/
public interface CommentService {

    List<Comment> listCommentByEplatformId(Long eplatformId);

    Comment saveComment(Comment comment);

}
