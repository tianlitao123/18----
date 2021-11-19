package com.example.zhihu.controller;

import com.example.zhihu.mapper.Area_info_mapper;
import com.example.zhihu.model.Area_info;
import com.example.zhihu.model.Result;
import com.example.zhihu.service.Area_info_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @data 2021年10月20日10:30
 */
@RestController
public class Area_info_controller {

    @Autowired
    Area_info_service area_info_service;

//@RequestParam("id")int id, @RequestBody List list
//@RequestBody Map<String, List<Map<String,Integer>>> map
    @ResponseBody
    @PostMapping("/area_info/insert")
    public Result insert(@RequestBody List<Area_info> list){
//        System.out.println(id+"    " +list);
//        Integer id = null;
//        List list = null;
//        for(String key : map.keySet()){
//            id = Integer.parseInt(key);
//            list = map.get(key);
//        }
//        System.out.println(id + "    " + list.get(0));


        return area_info_service.insert(list);

    }

//暂无使用价值
//    @ResponseBody
//    @GetMapping("/area_info/getArea_infoByIdAndId2")
//    public Result getArea_infoByIdAndId2(Integer id, String id2){
//        return area_info_service.getArea_infoByIdAndId2(id, id2);
//    }

//   获取某个榜单某年某月的排序
    @ResponseBody
    @GetMapping("/area_info/getUsersInfoByAreasId")
    public Result getUsersInfoByAreasId(int id, int year, int month){
        return area_info_service.getUsersInfoByAreasId(id, year, month);
    }

    @ResponseBody
    @GetMapping("/area_info/getAllListInfo")
    public Result getAllListInfo(int year, int month){
        return area_info_service.getAllListInfo(year, month);
    }

}
