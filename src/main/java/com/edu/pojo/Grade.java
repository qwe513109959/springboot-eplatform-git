package com.edu.pojo;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: spring-boot-englishplatform-01
 * @description: 等级
 * @author: Mr.jia
 * @date: 2020-03-26 14:39
 **/

@Entity
@Table(name = "tbl_grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @OneToMany(mappedBy = "grade")
//    private List<EnglishPlatform> englishPlatforms = new ArrayList<>();

    @ManyToMany(mappedBy = "grade")
    private List<EnglishPlatform> englishPlatforms = new ArrayList<>();

    public Grade() {
    }

    @Override
    public String toString() {
        return "EduTypes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", englishPlatforms=" + englishPlatforms +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EnglishPlatform> getEnglishPlatforms() {
        return englishPlatforms;
    }

    public void setEnglishPlatforms(List<EnglishPlatform> englishPlatforms) {
        this.englishPlatforms = englishPlatforms;
    }
}

