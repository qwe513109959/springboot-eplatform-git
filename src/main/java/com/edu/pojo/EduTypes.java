package com.edu.pojo;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: spring-boot-englishplatform-01
 * @description:
 * @author: Mr.jia
 * @date: 2020-03-26 14:39
 **/

@Entity
@Table(name = "tbl_edutype")
public class EduTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="name不能为空")
    private String name;

    @OneToMany(mappedBy = "edu_type")
    private List<EnglishPlatform> englishPlatforms  = new ArrayList<>();

    public EduTypes() {
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

