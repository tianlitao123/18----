package com.example.zhihu.service;

import com.example.zhihu.mapper.Location_mapper;
import com.example.zhihu.tools.enums.Coding;
import com.example.zhihu.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @data 2021年10月19日9:17
 *
 *
 */
@Service

public class Location_service  {
    @Autowired
    public Location_mapper location_mapper;

    @Autowired
    public Result result;

//  插入地点   location_code不是自增字段，  这里需要手动传入数字

    public Result insert(String name){
//        检查数据库中是否存在该数据
        if(location_mapper.get_location_code(name) != null){
            result.setCode(Coding.INSERT_FAIL);
            result.setMessage("数据库中已经存在该数据" + name);
            result.setData(null);
        }
        else{
            location_mapper.insert(name);
            result.setCode(Coding.INSERT_SUCCESS);
            result.setMessage("数据保存成功" + name);
            result.setData(null);
        }

//        System.out.println(location_mapper.get_location_code("北京"));
//        System.out.println(location_mapper.get_location_code("asdf"));
        return result;
    }

    //根据地点名称查询location_code
//  注意：此处result.data中返回值为Integer
    public Result get_location_code(String name){
        Integer location_code = location_mapper.get_location_code(name);
        if(location_code == null){
            result.setCode(Coding.FAIL);
            result.setMessage("数据库中不存在地点： + " + name);
            result.setData(null);
        }else{
            result.setCode(Coding.SUCCESS);
            result.setMessage("查询成功");
            result.setData(location_code);
        }
        return result;
    }

//根据code查询地点名称
    public Result get_location_name(int location_code){
        String location_name = location_mapper.get_location_name(location_code);
        if(location_name == null){
            result.setCode(Coding.FAIL);
            result.setMessage("数据库中不存在该location_code：" + location_code);
            result.setData(null);
        }else{
            result.setCode(Coding.SUCCESS);
            result.setMessage("查询成功");
            result.setData(location_name);
        }
        return result;
    }

}
