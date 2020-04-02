package com.edu.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: spring-boot-englishplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-26 14:37
 **/
@Entity
@Table(name = "tbl_eplatefrom")
public class EnglishPlatform {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    // 大数据类型加的注释,等同于Long
    @Basic
    @Lob
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne
    private EduTypes edu_type;

//    @ManyToOne
//    private Grade grade;

    // 多对多关系，多个级别
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Grade> grade = new ArrayList<>();

    @ManyToOne
    private User user;

    // 不会进入数据库
    @Transient
    private String gradeIds;

//    @OneToMany(mappedBy = "eplatform")
//    private List<Comment> comment = new ArrayList<>();

    //   List comments
//   List tags


    public EnglishPlatform() {
    }

    @Override
    public String toString() {
        return "EnglishPlatform{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", edu_type=" + edu_type +
                ", user=" + user +
                '}';
    }

    public void init() {
        this.gradeIds = tagsToIds(this.getGrade());
    }

    //1,2,3
    private String tagsToIds(List<Grade> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Grade tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return gradeIds;
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public EduTypes getEdu_type() {
        return edu_type;
    }

    public void setEdu_type(EduTypes edu_type) {
        this.edu_type = edu_type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Grade getGrade() {
//        return grade;
//    }
//
//    public void setGrade(Grade grade) {
//        this.grade = grade;
//    }


    public List<Grade> getGrade() {
        return grade;
    }

    public void setGrade(List<Grade> grade) {
        this.grade = grade;
    }

    public String getGradeIds() {
        return gradeIds;
    }

    public void setGradeIds(String gradeIds) {
        this.gradeIds = gradeIds;
    }
}
