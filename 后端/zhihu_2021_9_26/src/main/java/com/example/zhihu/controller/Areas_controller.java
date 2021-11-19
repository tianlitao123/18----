package com.example.zhihu.controller;

import com.example.zhihu.model.Areas;
import com.example.zhihu.model.Result;
import com.example.zhihu.service.Areas_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @data 2021年10月20日13:34
 */
@RestController
public class Areas_controller {

    @Autowired
    Areas areas;

    @Autowired
    Areas_service areas_service;

    @ResponseBody
    @PostMapping("/areas/insert")
    public Result insert(@RequestBody Areas areas){
        return areas_service.insert(areas);
    }

    @ResponseBody
    @GetMapping("/areas/getAllAreas")
    public Result getAllAreas(){
        return areas_service.getAllAreas();
    }

}
