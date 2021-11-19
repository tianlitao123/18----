package com.example.zhihu.controller;

import com.example.zhihu.model.Result;
import com.example.zhihu.service.Location_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.Location;

/**
 * @data 2021年10月20日19:27
 */
@RestController
public class Location_controller {
    @Autowired
    Location_service location_service;

    @ResponseBody
    @PostMapping("/location/insert")
    public Result insert( String name){
        return location_service.insert(name);
    }

    @ResponseBody
    @GetMapping("/location/getName")
    public Result getName(int location_code){
        return location_service.get_location_name(location_code);
    }

    @ResponseBody
    @GetMapping("/location/getLocation_code")
    public Result getLocation_code(String name){
        return location_service.get_location_code(name);
    }
}
