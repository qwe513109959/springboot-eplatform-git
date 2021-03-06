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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // 大数据类型加的注释,等同于Long
    @Basic
    @Lob
    private String content;
    private String firstPicture;
    private Integer views;
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

    @OneToMany(mappedBy = "eplatform")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    private User user;

    // 不会进入数据库
    @Transient
    private String gradeIds;

    private String description;


    public EnglishPlatform() {
    }

    @Override
    public String toString() {
        return "EnglishPlatform{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", views=" + views +
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
        this.gradeIds = gradesToIds(this.getGrade());
    }

    //1,2,3
    private String gradesToIds(List<Grade> grades) {
        if (!grades.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Grade grade : grades) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(grade.getId());
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

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
