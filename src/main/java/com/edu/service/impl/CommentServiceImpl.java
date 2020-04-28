package com.edu.service.impl;

import com.edu.dao.CommentRepository;
import com.edu.pojo.Comment;
import com.edu.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: spring-boot-eplatform
 * @description: 评论
 * @author: Mr.jia
 * @date: 2020-04-05 17:06
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository repository;

    /** 
    * @Description: 根据Id 列表显示评论
    * @Param: 
    * @Author: Mr.Jia 
    * @Date: 2020/4/5 5:09 下午
    */ 
    @Override
    public List<Comment> listCommentByEplatformId(Long eplatformId) {
        Sort sort = Sort.by(Sort.Direction.ASC,"createTime");
        // 将parentComment为空的加入到comments集合中
        List<Comment> comments = repository.findByEplatformIdAndParentCommentNull(eplatformId, sort);
        return eachComment(comments);
    }

    /** 
    * @Description: 保存评论到数据库
    * @Param: 
    * @Author: Mr.Jia 
    * @Date: 2020/4/5 5:17 下午 
    */
    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        // 获取父节点parentcomment的id
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1) {
            // 获取父节点的id
            comment.setParentComment(repository.getOne(parentCommentId));
        } else {
            // 无父节点，设为null，自己是父节点
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return repository.save(comment);
    }

    /**
     * 循环每个顶级的评论节点
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        // 将每个顶级评论结点copy到新对象中，不修改本对象的内容
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     * 合并评论的各层子代到第一级子代集合中
     * @param comments root根节点，eplatform不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            // 获得顶级评论结点的多个回复replys
            List<Comment> replys1 = comment.getReplyComments();
            for(Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();
    /**
     * 递归迭代，剥洋葱
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (comment.getReplyComments().size()>0) {
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }

}
