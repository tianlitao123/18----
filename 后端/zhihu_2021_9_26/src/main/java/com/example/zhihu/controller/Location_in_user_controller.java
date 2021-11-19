package com.example.zhihu.controller;

import com.example.zhihu.model.Result;
import com.example.zhihu.service.Location_in_user_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RelationSupport;

/**
 * @data 2021年10月20日20:00
 */
@RestController
public class Location_in_user_controller {
    @Autowired
    Location_in_user_service location;


    @ResponseBody
    @GetMapping("/location_in_user/getLocations")
    public Result getLocations(String id2){
        return location.get_user_locations(id2);
    }

}
