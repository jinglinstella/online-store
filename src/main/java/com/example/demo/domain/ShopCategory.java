package com.example.demo.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class ShopCategory {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long shopCategoryId;
    private String shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;
    private Integer priority;
    private Date timeCreated;
    private Date timeUpdated;
//    private ShopCategory parent;

    public ShopCategory(){


    }

    public ShopCategory(String name){
        this.shopCategoryName = name;

    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    @OneToMany(
            mappedBy = "shopCategory",
            cascade = CascadeType.ALL,
            orphanRemoval = true

    )
//    private Set<Shop> shops = new HashSet<>();
    private List<Shop> shops = new ArrayList<>();

    public Long getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(Long shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
    }

    public String getShopCategoryDesc() {
        return shopCategoryDesc;
    }

    public void setShopCategoryDesc(String shopCategoryDesc) {
        this.shopCategoryDesc = shopCategoryDesc;
    }

    public String getShopCategoryImg() {
        return shopCategoryImg;
    }

    public void setShopCategoryImg(String shopCategoryImg) {
        this.shopCategoryImg = shopCategoryImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Date getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(Date timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

//    public ShopCategory getParent() {
//        return parent;
//    }
//
//    public void setParent(ShopCategory parent) {
//        this.parent = parent;
//    }
}

