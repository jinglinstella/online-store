package com.example.demo.controllers;

import com.example.demo.domain.Area;
import com.example.demo.service.AreaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/admin")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    private String testHello() {
        return "HelloWorld from spring controller";
    }

    @RequestMapping(value = "/listarea", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listArea(){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Area> areas = new ArrayList<Area>();
        try {
            areas = areaService.findAll();
            modelMap.put("data", areas);
            modelMap.put("total", areas.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg",e.toString());
        }
        return modelMap;
    }


}
