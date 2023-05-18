package com.example.demo.service.impl;

import com.example.demo.domain.Area;
import com.example.demo.domain.Shop;
import com.example.demo.domain.ShopCategory;
import com.example.demo.dto.ImageHolder;
import com.example.demo.dto.ShopDto;
import com.example.demo.repository.ShopCategoryRepository;
import com.example.demo.repository.ShopRepository;
import com.example.demo.service.ShopCategoryService;
import com.example.demo.service.ShopService;
import com.example.demo.util.ImageUtil;
//import com.example.demo.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    private final ShopCategoryRepository shopCategoryRepository;

    public ShopCategoryServiceImpl(ShopCategoryRepository shopCategoryRepository){
        this.shopCategoryRepository = shopCategoryRepository;
    }

    @Override
    public ShopCategory findById(Long aLong) {

        return shopCategoryRepository.findById(aLong).orElse(null);
    }

    @Override
    public List<ShopCategory> getShopCategoryList(){
        List<ShopCategory> shopCategories = new ArrayList<>();
        shopCategoryRepository.findAll().forEach(shopCategories::add);
//        System.out.println("areaRepo count: "+areaRepository.count());
        System.out.println("Fetched shop categories: " + shopCategories);
        return shopCategories;

    }

    @Override
    public ShopCategory save(ShopCategory object) {
        return shopCategoryRepository.save(object);
    }

    @Override
    public void delete(ShopCategory object) {
        shopCategoryRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        shopCategoryRepository.deleteById(aLong);
    }
}
