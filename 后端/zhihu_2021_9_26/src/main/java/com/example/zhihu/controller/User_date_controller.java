package com.example.zhihu.controller;

import com.example.zhihu.model.Result;
import com.example.zhihu.model.User_date;
import com.example.zhihu.service.User_date_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationSupport;
import java.util.List;

/**
 * @data 2021年10月25日21:44
 */
@RestController
public class User_date_controller {
    @Autowired
    private User_date_service user_date_service;

    @ResponseBody
    @PostMapping("/User_date/insert")
    public Result insert(@RequestBody List<User_date> list){
        return user_date_service.insert(list);
    }

    @ResponseBody
    @GetMapping("/User_date/getUserAndDate")
    public Result getUserAndDateById2(@RequestParam String id2){
        return user_date_service.getUserAndDate(id2);
    }

}
