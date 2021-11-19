package com.example.zhihu.controller;

import com.example.zhihu.model.Result;
import com.example.zhihu.model.User_check;
import com.example.zhihu.service.User_check_service;
import com.example.zhihu.service.User_date_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationSupport;
import java.util.List;

/**
 * @data 2021年10月25日21:44
 */
@RestController
public class User_check_controller {
    @Autowired
    private User_check_service user_check_service;

    @ResponseBody
    @GetMapping("User_check/getFrequencyById2")
    public Result getFrequencyById2(@RequestParam String id2){
        return user_check_service.getFrequencyById2(id2);
    }

    @ResponseBody
    @PostMapping("User_check/insert")
    public Result insert(@RequestBody List<User_check> list){
        return user_check_service.insert(list);
    }

//    获取用户被检索表   该数据已存储至area_info表中   改接口暂无实际意义
    @ResponseBody
    @RequestMapping(value = "User_check/getFrequencyList", method = RequestMethod.GET)
    public Result getNameAndFrequency(){
        return user_check_service.getFrequencyList();
    }

    @ResponseBody
    @GetMapping("/user_check/getAllUser")
    public Result getAllUser(){
        return user_check_service.getAllUser();
    }

    @ResponseBody
    @GetMapping("/user_check/getUserById2")
    public Result getUserById2(@RequestParam String id2){
        return user_check_service.getUserById2(id2);
    }

}
