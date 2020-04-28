package com.edu.web;

import com.edu.pojo.Comment;
import com.edu.pojo.EnglishPlatform;
import com.edu.pojo.User;
import com.edu.service.CommentService;
import com.edu.service.EplatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


/**
 * @program: spring-boot-eplatform
 * @description: 评论
 * @author: Mr.jia
 * @date: 2020-04-05 16:46
 **/
@Controller
public class IndexCommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    EplatformService eplatformService;

    @Value("${comment.avatar}")
    private String avatar;

    /** 
    * @Description: 列表显示 commentList 片段
    * @Param: 
    * @Author: Mr.Jia 
    * @Date: 2020/4/5 5:09 下午 
    */
    @GetMapping("/comments/{eplatformId}")
    public String list(@PathVariable Long eplatformId, Model model){
        model.addAttribute("comments", commentService.listCommentByEplatformId(eplatformId));
        return "eplatform :: commentList"; // 返回/eplatform 下的commentList片段,只刷新列表
    }

    /** 
    * @Description: 发布并保存评论
    * @Param: 
    * @Author: Mr.Jia 
    * @Date: 2020/4/5 5:10 下午 
    */
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long eplatformId = comment.getEplatform().getId();
        // 根据eplatform的id查询到service，进行保存
        comment.setEplatform(eplatformService.getEplatform(eplatformId));
        // 获取管理员身份信息
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + eplatformId;
    }



}
