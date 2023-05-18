package com.example.demo.controllers;

import com.example.demo.domain.Area;
import com.example.demo.domain.Owner;
import com.example.demo.domain.Shop;
import com.example.demo.domain.ShopCategory;
import com.example.demo.dto.ImageHolder;
import com.example.demo.dto.ShopDto;
import com.example.demo.repository.ShopRepository;
import com.example.demo.service.AreaService;
import com.example.demo.service.OwnerService;
import com.example.demo.service.ShopCategoryService;
import com.example.demo.service.ShopService;
import com.example.demo.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



@Controller
//@RequestMapping("/shopadmin")
public class ShopController {

    private final ShopRepository shopRepository;

    @Autowired
    ShopService shopService;
    
    @Autowired
    private OwnerService ownerService;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private AreaService areaService;

    public ShopController(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
        //will inject an instance of shopRepository into the controller

    }

    @RequestMapping(value = "/shopoperation", method = RequestMethod.GET)
    public String shopOperation() {
        return "shopoperation";
    }
    
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }
    
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return "main";
    }

    @RequestMapping("/shops")
    public String getShops(Model model) {
        //will return to the view
        //view will get a copy of the model
        model.addAttribute("shops", shopRepository.findAll());
        //when hit "/shops" url, spring will run getShops.
        //it will provide the getShops method with a model object
        //for that model we will add an attribute called shops
        //this model will get returned back to view layer with attribute name "shops" and a list of shops
        //we are using the repository to get a list of books out of the database
        return "shops/list";
        //tell view resolver to look for list under shops
        //that's how view resolver find the ThymeLeaf template and render HTML to our client
        //TL is able to take an object out of the database using spring data jpa, run it through spring mvc through TL view layer
        //where we have a list of POJO and render them on the webpage
    }

    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInitInfo() {
        System.out.println("calling getshopinitinfo in shopcontroller");
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
        List<Area> areaList = new ArrayList<Area>();
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList();
            areaList = areaService.findAll();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //check Kaptcha
//        if (!CodeUtil.checkVerifyCode(request)) {
//            modelMap.put("success", false);
//            modelMap.put("errMsg", "Wrong Kapture!");
//            return modelMap;
//        }
        // 1.get and transform all input data
        //for shop object
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        System.out.println("shopStr: " + shopStr);
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
//            shop = mapper.readValue(shopStr, Shop.class);
            JsonNode root = mapper.readTree(shopStr);
            shop = new Shop();
            shop.setShopName(root.get("shopName").asText());
            shop.setShopAddr(root.get("shopAddr").asText());
            shop.setPhone(root.get("phone").asText());
            shop.setShopDesc(root.get("shopDesc").asText());

            Long shopCategoryId = root.get("shopCategory").get("shopCategoryId").asLong();
            ShopCategory shopCategory = shopCategoryService.findById(shopCategoryId);
            shop.setShopCategory(shopCategory);

            Long areaId = root.get("area").get("areaId").asLong();
            Area area = areaService.findById(areaId);
            shop.setArea(area);
            System.out.println("areaname: "+shop.getArea().getAreaName());
            
//            Long areaId = root.get("areaId").asLong();
//            Area area = areaService.findById(areaId);
//            shop.setArea(area);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        // for image
        //request is from the front end
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

//        MultipartFile shopImg = null;
//        MultipartResolver multipartResolver = new StandardServletMultipartResolver(request.getSession().getServletContext());

        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
//            shopImg = multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "can't upload empty image");
            return modelMap;
        }

        // 2.register shop
        
        Long userId = (Long) request.getSession().getAttribute("userId");
        Owner owner = ownerService.getOwnerById(userId);
        
        if (shop != null && shopImg != null) {
            // TODO: manually set owner info here, but should get it from session in the future
//			User owner = (User) request.getSession().getAttribute("user");
//        	Owner owner = (Owner) request.getSession().getAttribute("user");
//            Owner owner = new Owner();
//            owner.setUserId(1L);
            shop.setOwner(owner);
            ShopDto shopDto;

            try {
                ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(),shopImg.getInputStream());
                shopDto = shopService.addShop(shop,imageHolder);
                if (shopDto.getStateInfo() == "verifying info provided") {
                    modelMap.put("success", true);

                    @SuppressWarnings("unchecked")
                    List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                    if (shopList == null || shopList.size() == 0) {
                        shopList = new ArrayList<Shop>();
                    }
                    shopList.add(shopDto.getShop());
                    request.getSession().setAttribute("shopList", shopList);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", shopDto.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "can't insert empty shop");
            return modelMap;
        }
    }


}
