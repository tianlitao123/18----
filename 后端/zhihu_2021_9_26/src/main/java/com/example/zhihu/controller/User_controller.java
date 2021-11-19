package com.example.zhihu.controller;



import com.example.zhihu.model.Area_info;
import com.example.zhihu.model.Result;
import com.example.zhihu.model.User;
import com.example.zhihu.service.User_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @data 2021年10月18日9:04
 */
@RestController
public class User_controller {



    @Autowired
    private Result result;

    @Autowired
    private User_service user_service;

//有待商榷   请求时间过长    分页请求
    @ResponseBody
    @GetMapping(value = "/User/get_allByPage")
    public Result get_all(@RequestParam int page, @RequestParam int page_size){
        return user_service.get_all(page, page_size);
    }
//不加分页  直接请求
    @ResponseBody
    @GetMapping(value = "/User/get_all")
    public Result get_all(){
        return user_service.get_all();
    }

    //    限定每次插入数量为1000
    @ResponseBody
    @PostMapping("/User/insert")
    public Result insert(@RequestBody List<User> list){
        return user_service.insert(list);
    }

//    获取用户活跃表
//    @ResponseBody
//    @PostMapping("/User/getActivityTable")
//    public Result getActivityTable(){
//        return ;
//    }


}
