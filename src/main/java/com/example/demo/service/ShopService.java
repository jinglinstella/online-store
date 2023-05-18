package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Shop;
import com.example.demo.dto.ImageHolder;
import com.example.demo.dto.ShopDto;

public interface ShopService {

    ShopDto addShop(Shop shop, ImageHolder thumbnail);
    Shop getByShopId(long shopId);

    ShopDto modifyShop(Shop shop, ImageHolder thumbnail);
//
//    ShopDto getShopList(Shop shopCondition, int pageIndex, int pageSize);
//	Shop getShopsByUserId(Long userId);
	
	List<Shop> getShopsByUserId(Long userId);
}
