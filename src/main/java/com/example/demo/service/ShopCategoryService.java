package com.example.demo.service;

import com.example.demo.domain.Area;
import com.example.demo.domain.ShopCategory;

import java.util.List;

//public interface ShopCategoryService extends CrudService<ShopCategory, Long>{
//}

public interface ShopCategoryService{

    List<ShopCategory> getShopCategoryList();

    ShopCategory findById(Long aLong);

    ShopCategory save(ShopCategory object);

    void delete(ShopCategory object);

    void deleteById(Long aLong);

//    ShopCategory getShopCategoryById(Long shopCategoryId);
}
