package com.example.zhihu.service;

import com.example.zhihu.mapper.Areas_mapper;
import com.example.zhihu.model.Areas;
import com.example.zhihu.tools.enums.Coding;
import com.example.zhihu.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @data 2021年10月19日22:14
 */
@Service
public class Areas_service {
    @Autowired
    Areas areas;

    @Autowired
    Result result;

    @Autowired
    Areas_mapper areas_mapper;

//    判断插入操作是否成功
    int id = -1;
    public Result insert(Areas areas){
//        数据库中不存在该条数据
        if(areas_mapper.getAreasByName(areas.getName())==null){
//      数据 -->  数据库
            areas_mapper.insert(areas);
            id = areas.getId();
//            System.out.println(id);
            if(id < 0){
                result.setCode(Coding.INSERT_FAIL);
                result.setMessage("插入数据操作有误{" + areas.toString() + "}");
                result.setData(null);
            }
            else{
                result.setCode(Coding.INSERT_SUCCESS);
                result.setMessage("数据已保存{" + areas.toString() + "}");
                result.setData(null);
            }
        }else{
            result.setCode(Coding.INSERT_FAIL);
            result.setMessage("数据库中已经存在该条数据{" + areas.toString() + "}");
            result.setData(null);
        }
        return result;

    }


    public Result getDataById(int id){
        List list = areas_mapper.getAreasById(id);
        if(list.isEmpty()){
            result.setCode(Coding.FAIL);
            result.setMessage("未查询到该数据  id为：" + id);
            result.setData(null);
        }
        else{
            result.setCode(Coding.SUCCESS);
            result.setMessage("查询成功");
            result.setData(list);
        }
        return result;
    }

    public Result getIdByData(String name){
        Integer id = areas_mapper.getAreasByName(name);
        if(id == null){
            result.setCode(Coding.FAIL);
            result.setMessage("未查询到该类名称：" + name);
            result.setData(null);
        }else{
            result.setCode(Coding.SUCCESS);
            result.setMessage("查询成功");
            result.setData(id);
        }
        return result;
    }


    public Result getAllAreas(){
        List<Areas> list = areas_mapper.getAllAreas();
        if(list.isEmpty()){
            result.setCode(Coding.FAIL);
            result.setMessage("未查询到数据");
            result.setData(null);
        }else{
            result.setCode(Coding.SUCCESS);
            result.setMessage("查询成功");
            result.setData(list);
        }
        return result;
    }
}
