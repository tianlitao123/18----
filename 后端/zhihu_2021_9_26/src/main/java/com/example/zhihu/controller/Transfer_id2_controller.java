package com.example.zhihu.controller;

import com.example.zhihu.mapper.User_check_mapper;
import com.example.zhihu.mapper.User_mapper;
import com.example.zhihu.model.Result;
import com.example.zhihu.service.User_check_service;
import com.example.zhihu.tools.Transfer_id2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @data 2021年10月26日21:46
 */
@RestController
public class Transfer_id2_controller {

    @Autowired
    Result result;

    @Autowired
    Transfer_id2 transfer_id2;
//    Transfer_id2 transfer_id2 = new Transfer_id2();

    @Autowired
    User_check_service user_check_service;

    @Autowired
    private User_mapper user_mapper;

    @Autowired
    User_check_mapper user_check_mapper;

//    Transfer_id2 transfer_id2 = new Transfer_id2();
//    检索一个用户  将该用户id传递给爬虫  爬取数据后存储在user_check中
//    搜索用户时   再service层将程序锁定  每次只允许一个进程访问
    @ResponseBody
    @GetMapping("/searchUser")
    public Result searchUser(@RequestParam String id2) throws InterruptedException {
        int frequency=0;
//      如果用户已经存在表中
        if(!user_check_mapper.getUserById2(id2).isEmpty()){
            frequency = user_check_mapper.getFrequencyById2(id2).get(0).getFrequency();
        }
        transfer_id2.read_file(id2);
        result = user_check_service.searchUser(id2, frequency);
        return result;
    }
}
