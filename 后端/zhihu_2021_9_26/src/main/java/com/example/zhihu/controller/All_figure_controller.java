package com.example.zhihu.controller;

import com.example.zhihu.model.All_figure;
import com.example.zhihu.model.Result;
import com.example.zhihu.service.All_figure_service;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @data 2021年11月01日18:20
 */
@RestController
public class All_figure_controller {

    @Autowired
    All_figure_service all_figure_service;
/**
 * 该super_table暂时取消
 * @date 2021/11/9 11:01
 * @param list
 * @return com.example.zhihu.model.Result
 */
//    @ResponseBody
//    @GetMapping("/all_figure/insert_super")
//    public Result insert_superTable(@RequestParam String name){
//        return all_figure_service.insert_superTable(name);
//    }

    @ResponseBody
    @PostMapping("/all_figure/insert_user_draw")
    public Result insert_userDraw(@RequestBody List<All_figure> list){
        return all_figure_service.insert_userDraw(list);
    }

    @ResponseBody
    @PostMapping("all_figure/get_userDraw")
    public Result get_userDraw(@RequestBody All_figure all_figure){
//        System.out.println(all_figure.toString());
        return all_figure_service.get_userDraw(all_figure);
    }

//    @ResponseBody
//    @PostMapping("all_figure/get_userDrawByTime")
//    public Result get_userDrawByTime(@RequestBody All_figure all_figure){
////        return all_figure_service.get_userDrawByTime(all_figure);
//    }
}
