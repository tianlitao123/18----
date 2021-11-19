package com.example.zhihu.service;

import com.example.zhihu.mapper.Location_in_user_mapper;
import com.example.zhihu.tools.enums.Coding;
import com.example.zhihu.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @data 2021年10月17日15:16
 */
@Service
public class Location_in_user_service  {

    @Autowired
    private Result result;

    @Autowired
    private Location_in_user_mapper location_in_user_mapper;


//保存用户的地点

    public Result insert(String id2, String location){
        location_in_user_mapper.insert(id2, location);
        List list = location_in_user_mapper.get_user_locations(id2);
        if(list.isEmpty()){
            result.setCode(Coding.FAIL);
            result.setMessage("地址插入失败");
            result.setData(null);
        }
        else{
            result.setCode(Coding.SUCCESS);
            result.setMessage("地址插入成功");
            result.setData(null);
        }
        return result;
    }
//根据用户的id1 得到用户所有所在地

    public Result get_user_locations(String id2){
        List list = location_in_user_mapper.get_user_locations(id2);
        if(list.isEmpty()){
            result.setCode(Coding.FAIL);
            result.setMessage("不存在该id" + id2);
            result.setData(null);
        }else{
            result.setCode(Coding.SUCCESS);
            result.setMessage("查询成功");
            result.setData(list);
        }
        return result;
    }

//    public boolean isExistUser(String id2){
//        if(location_in_user_mapper.get_user_locations(id2).isEmpty()){
//            return false;
//        }
//        else{
//            return true;
//        }
//    }

}
