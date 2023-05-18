package com.example.demo.bootstrap;

import com.example.demo.domain.Area;
import com.example.demo.domain.Shop;
import com.example.demo.domain.Owner;
import com.example.demo.domain.ShopCategory;
import com.example.demo.repository.AreaRepository;
import com.example.demo.repository.ShopCategoryRepository;
import com.example.demo.repository.ShopRepository;
import com.example.demo.repository.OwnerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AreaRepository areaRepository;
    private final ShopRepository shopRepository;

    private final OwnerRepository userRepository;

    private final ShopCategoryRepository shopCategoryRepository;

    public BootStrapData(AreaRepository areaRepository, ShopRepository shopRepository,
                         OwnerRepository userRepository, ShopCategoryRepository shopCategoryRepository) {
        this.areaRepository = areaRepository;
        this.shopRepository = shopRepository;
        this.userRepository = userRepository;
        this.shopCategoryRepository = shopCategoryRepository;
    }

    @Override
    public void run(String... args) throws Exception{

        Owner user1=new Owner();
        user1.setName("Eric");

        Area usa = new Area("USA",1);
        Shop shop1 = new Shop();
        usa.getShops().add(shop1);
        shop1.setArea(usa);

        shop1.setOwner(user1);
        user1.getShops().add(shop1);

        ShopCategory sc1 = new ShopCategory("book");
        shop1.setShopCategory(sc1);
        sc1.getShops().add(shop1);

        //save to H2 database
        areaRepository.save(usa);
        shopRepository.save(shop1);
        userRepository.save(user1);
        shopCategoryRepository.save(sc1);

        System.out.println("started in bootstrap");
        System.out.println("number of areas: "+areaRepository.count());
        System.out.println("number of shops: "+shopRepository.count());
        System.out.println("User1's number of shops: "+user1.getShops().size());
        System.out.println("shop1's category: "+shop1.getShopCategory().getShopCategoryName());


    }
}
