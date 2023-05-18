package com.example.demo.controllers;

import com.example.demo.domain.Owner;
import com.example.demo.domain.Shop;
import com.example.demo.service.OwnerService;
import com.example.demo.service.ShopService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class OwnerController {
	
    @Autowired
    private OwnerService ownerService;
    
    @Autowired
    private ShopService shopService;
    
    @RequestMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }
    
    @RequestMapping("/getshopbyuser")
    @ResponseBody
    public List<Shop> getShopsByUser(@RequestParam("userId") Long userId) {
        System.out.println("Received userId: " + userId);
        List<Shop> shops = shopService.getShopsByUserId(userId);
        System.out.println("Fetched shops: " + shops);
        System.out.println("get shop area: "+shops.get(0).getArea().getAreaName());
        return shops;
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("name") String name,
//                               @RequestParam("profileImg") String profileImg,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("gender") String gender
//                               @RequestParam("enableStatus") Integer enableStatus,
//                               @RequestParam("userType") Integer userType
                               ) {
        Owner owner = new Owner();
        owner.setName(name);
//        owner.setProfileImg(profileImg);
        owner.setEmail(email);
        owner.setPassword(password);
        owner.setGender(gender);
//        owner.setEnableStatus(enableStatus);
//        owner.setUserType(userType);

        ownerService.registerUser(owner);

        return "redirect:/login";
    }
    
    @RequestMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email,
                             @RequestParam("password") String password, Model model,
                             HttpSession session) {
    	System.out.println("initializing postmapping login....");
        Owner owner = ownerService.login(email, password);
        System.out.println("ownerid: " + owner.getOwnerId());
        if (owner != null) {
            session.setAttribute("userId", owner.getOwnerId());
            System.out.println("userId from session: " + owner.getOwnerId());
            return "welcome";
        } else {
            model.addAttribute("errorMessage", "Invalid email or password");
            return "login";
        }
    }
    
    @RequestMapping("/welcome")
    public String showWelcomePage(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId != null) {
            model.addAttribute("userId", userId);
            return "welcome";
        } else {
            return "redirect:/login";
        }
    }
	
	

}
