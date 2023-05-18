package com.example.demo.service.impl;

import com.example.demo.domain.Area;
import com.example.demo.domain.Shop;
import com.example.demo.domain.ShopCategory;
import com.example.demo.dto.ImageHolder;
import com.example.demo.dto.ShopDto;
import com.example.demo.repository.AreaRepository;
import com.example.demo.repository.ShopCategoryRepository;
import com.example.demo.repository.ShopRepository;
import com.example.demo.service.ShopService;
import com.example.demo.util.ImageUtil;
//import com.example.demo.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service

public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private ShopCategoryRepository shopCategoryRepository;
    
    @Override
    public List<Shop> getShopsByUserId(Long userId) {
    	 return shopRepository.findByOwnerId(userId);
    }

    @Override
    @Transactional
    public ShopDto addShop(Shop shop, ImageHolder thumbnail) {
        // empty shop input
        if (shop == null) {
            return new ShopDto(false,"shop is null");
        }

        shop.setEnableStatus(0);
        shop.setTimeCreated(new Date());
        shop.setTimeUpdated(new Date());

        Area area = shop.getArea();
        if (area != null && area.getAreaId() != null) {
            // Merge the detached Area entity using the AreaRepository
            Area managedArea = areaRepository.save(area);
            shop.setArea(managedArea);
        }

        shopRepository.save(shop);
        System.out.println("Shop saved with ID: " + shop.getShopId() + " and ownerId: " + shop.getOwner().getOwnerId());


        ShopCategory shopCategory = shop.getShopCategory();
        if (shopCategory != null && shopCategory.getShopCategoryId() != null) {
            // Merge the detached ShopCategory entity using the ShopCategoryRepository
            ShopCategory managedShopCategory = shopCategoryRepository.save(shopCategory);
            shop.setShopCategory(managedShopCategory);
        }

        if (thumbnail != null) {
            addShopImg(shop, thumbnail);
            shopRepository.save(shop);
            System.out.println("Shop saved with ID: " + shop.getShopId()); // Add this line to print the shop ID


        }
        return new ShopDto(true,"verifying info provided", shop);
    }

    private void addShopImg(Shop shop, ImageHolder thumbnail) {
    	String seperator = System.getProperty("file.separator");//different os will get different separator
    	
    	//set up relative address
    	long shopId = shop.getShopId();
    	String relativeTargetPath = "upload/images/item/shop/" + shopId + "/";
    	relativeTargetPath = relativeTargetPath.replace("/", seperator);
        
        //create destination directory where we store the images
        String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, relativeTargetPath);
        shop.setShopImg(shopImgAddr);
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopRepository.findById(shopId).orElse(null);
    }

    @Override
    @Transactional
    public ShopDto modifyShop(Shop shop, ImageHolder thumbnail) {

        return null;

    }



//    @Override
//    public ShopDto getShopList(Shop shopCondition, int pageIndex, int pageSize) {
//        //pageIndex -> rowIndex
//        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
//        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
//        int count = shopDao.queryShopCount(shopCondition);
//        ShopDto se = new ShopDto();
//        if (shopList != null) {
//            se.setShopList(shopList);
//            se.setCount(count);
//        } else {
//            se.setState(ShopStateEnum.INNER_ERROR.getState());
//        }
//        return se;
//    }





}
