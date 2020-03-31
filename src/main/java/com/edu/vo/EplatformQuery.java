package com.edu.vo;

/**
 * @program: spring-boot-eplatform-01
 * @description: 封装对象，查询的对象
 * @author: Mr.jia
 * @date: 2020-03-30 16:45
 **/
public class EplatformQuery {

    private String title;
    private Long typeId;
    private boolean recommend;

    public EplatformQuery() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
