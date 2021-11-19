package com.example.zhihu.service;

import com.example.zhihu.mapper.Area_info_mapper;
import com.example.zhihu.mapper.Areas_mapper;
import com.example.zhihu.model.Area_info;
import com.example.zhihu.tools.enums.Coding;
import com.example.zhihu.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.awt.geom.Area;
import java.util.List;

/**
 * @data 2021年10月19日22:14
 *
 * bug : 该表中可以存入相同的数据
 */
@Service
public class Area_info_service {

    @Autowired
    Area_info area_info;

    @Autowired
    Areas_mapper areas_mapper;

    @Autowired
    Area_info_mapper area_info_mapper;

    @Autowired
    Result result;


    public Result insert(List<Area_info> list){
//      判断数据库中是否存在该分区

        for(Area_info area_info: list){
            area_info_mapper.insert(area_info);
        }
//        判断列表中某一个数据是否成功
        if(area_info_mapper.isExist(list.get(0)).isEmpty()){
            result.setCode(Coding.FAIL);
            result.setData(null);
            result.setMessage("数据存储失败");
        }else{
            result.setCode(Coding.SUCCESS);
            result.setData(null);
            result.setMessage("数据存储成功");
        }

//        if(temp_list.isEmpty()){
//            result.setCode(Coding.INSERT_FAIL);
//            result.setMessage("分区表中没有该榜单  id：" + id);
//            result.setData(null);
//        }
//        else {
////            for (Area_info area_info : list) {
//////               判断当前数据是否在数据库存在
////                if(area_info_mapper.isExist(area_info.getId(),area_info.getYear(), area_info.getMonth()).isEmpty()){
////                    area_info_mapper.insert(area_info);
////                }
////                else{
////                    result.setCode(Coding.INSERT_FAIL);
////                    result.setMessage("当前分区中已经存在该用户 id：" + area_info.getId2());
////                    result.setData(null);
////
////                    area_info_mapper.deleteByIdYearAndMonth(area_info);
////
////                    return result;
////                }
////            }
//
////            判断当前是否存在  若存在则将数据删除   然后重新保存最新数据
//            Area_info temp = list.get(0);
//            if(area_info_mapper.getUsersInfoByAreasId(temp.getId(), temp.getYear(), temp.getMonth()).isEmpty()){
//                for(Area_info area_info : list){
//                    area_info_mapper.insert(area_info);
//                }
//            }else{
//                area_info_mapper.deleteByIdYearAndMonth(area_info);
//                for(Area_info area_info : list){
//                    area_info_mapper.insert(area_info);
//                }
//            }
//
//            result.setCode(Coding.INSERT_SUCCESS);
//            result.setMessage("所有数据插入成功");
//            result.setData(null);
//        }
        return result;
    }
//      暂无使用价值
//    public Result getArea_infoByIdAndId2(Integer id, String id2){
//        List<Area_info> list = area_info_mapper.getAreaInfoByIdAndId2(id2, id);
//        if(list.isEmpty()){
//            result.setCode(Coding.FAIL);
//            result.setMessage("没有查询到该用户：" + area_info.toString());
//            result.setData(null);
//        }else{
//            result.setCode(Coding.SUCCESS);
//            result.setMessage("查询成功");
//            result.setData(list.get(0).getValue());
//        }
//        return result;
//    }
//  获取所有当前分区（id）的用户
    public Result getUsersInfoByAreasId(int id, int year, int month){
        List list = area_info_mapper.getUsersInfoByAreasId(id, year, month);
        if(list.isEmpty()){
            result.setCode(Coding.FAIL);
            result.setMessage("不存在该分区id：" + id + " 或者不存在该时间的数据");
            result.setData(null);
        }else{
            result.setCode(Coding.SUCCESS);
            result.setMessage("查询成功");
            result.setData(list);
        }
        return result;
    }
//获取某年某月  所有榜单数据
    public Result getAllListInfo(int year, int month){
        List list = area_info_mapper.getAllListInfo(year, month);
        if(list.isEmpty()){
            result.setCode(Coding.FAIL);
            result.setMessage("未查询到该时间的数据：year:" +  year + "month" + month );
            result.setData(null);
        }else{
            result.setCode(Coding.SUCCESS);
            result.setMessage("查询成功");
            result.setData(list);
        }

        return result;
    }

}
