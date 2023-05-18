package com.example.demo.dto;

import com.example.demo.domain.Shop;

import java.util.*;

public class ShopDto {

//    private int state;
    private String stateInfo;
    private int count;
    private Shop shop;
    private List<Shop> shopList;

    private boolean success;

    public ShopDto() {
    }

    // shop CRUD failed case
    public ShopDto(String stateInfo) {
//        this.state = stateEnum.getState();
        this.stateInfo = stateInfo;
    }

    public ShopDto(boolean success, String stateInfo) {
        this.success = success;
        this.stateInfo = stateInfo;
    }

    // shop CRUD success case
    public ShopDto(String stateInfo, Shop shop) {
//        this.state = stateEnum.getState();
        this.stateInfo = stateInfo;
        this.shop = shop;
    }

    public ShopDto(boolean success, String stateInfo, Shop shop) {
        this.success = success;
        this.stateInfo = stateInfo;
        this.shop = shop;
    }

    // shop CRUD success case
    public ShopDto(String stateInfo, List<Shop> shopList) {
        this.stateInfo = stateInfo;
        this.shopList = shopList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
