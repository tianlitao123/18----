package com.example.zhihu.service;

import com.example.zhihu.mapper.Area_info_mapper;
import com.example.zhihu.mapper.Location_in_user_mapper;
import com.example.zhihu.mapper.User_mapper;
import com.example.zhihu.model.Area_info;
import com.example.zhihu.model.User;
import com.example.zhihu.model.User_check;
import com.example.zhihu.model.showData.Bangdan;
import com.example.zhihu.tools.enums.Coding;
import com.example.zhihu.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @data 2021年10月17日10:11
 */

@Service
public class User_service {
    @Autowired
    private User user;

    @Autowired
    private Result result;

    @Autowired
    private User_mapper user_mapper;

    @Autowired
    private Location_in_user_mapper location_in_user_mapper;

    @Autowired
    private Area_info_mapper area_info_mapper;


    @Value("${page_size}")
    private int page_size;

//    @Value("${insert_size}")
//    private int insert_size;
//  将用户存入数据库

    public Result insert(List<User> list){
//        暂时取消
//        if(list.size() > this.insert_size){
//            result.setCode(Coding.INSERT_FAIL);
//            result.setMessage("插入数据超过" + insert_size);
//            result.setData(null);
//            return result;
//        }
//        判断当前用户是否存入成功  id初值为-1


//        批处理数据  插入数据库中
        user_mapper.insert(list);
        for(User user : list) {
//            user_mapper.insert(user);
//            System.out.println(user.toString());
            String id = user.getId2();
            /**
             * 这里将参数改编为user
             *
             * user.getId2(), user.getUrl_toke(), user.getName(), user.getGender(), user.getLevel_info_level(),
             *                     user.getAvatar_url(), user.getType(), user.getHeadline(), user.getDescription(), user.getBusiness_name(),
             *                     user.getFollowing_count(), user.getFollower_count(), user.getAnswer_count(), user.getZvideo_count(),
             *                     user.getQuestion(), user.getArticle_count(), user.getColumns_count(), user.getFavorite_count(), user.getFollowing_question_count(),
             *                     user.getFollowing_topic_count(), user.getColumns_count(), user.getFollowing_favlists_count(), user.getVoteup_count()
             *
             */


            //              存储用户位置   如果当前用户所在地在location_in_user中
            if(location_in_user_mapper.get_user_locations(id).isEmpty()){
//            插入地址
//            判断当前用户的位置是否已经存储   当前用户地理位置未入库
                if (user.getLocation().size() > 0) {
    //                BigInteger id1 = user_mapper.get_id1(user.getId2());
                    for(String location : user.getLocation()) {
    //                    判断当前用户的地理位置是否已经再数据库中存在   若不存在 则保存
                        if(location_in_user_mapper.isSaved(id, location).isEmpty()){
                            location_in_user_mapper.insert(id, location);
                        }
                        else{
                            continue;
                        }
                    }
                }
            }



            //判断插入数据是否成功
//            if(id == ""){
//                result.setCode(Coding.INSERT_FAIL);
//                result.setMessage("数据插入有误 " + user);
//                result.setData(null);
//                return result;
//            }else{
//                result.setCode(Coding.INSERT_SUCCESS);
//                result.setMessage("成功插入数据");
//                result.setData(null);
//
//            }
        }
        result.setCode(Coding.INSERT_SUCCESS);
        result.setMessage("成功插入数据");
        result.setData(null);
//每次在user表中保存数据时   统计其表中活跃榜单  并且保存在area——info中
        insert_ActivityTableIntoArea_info();
        return result;
    }
//   统计活跃榜 并且存入area——info中
    public void insert_ActivityTableIntoArea_info(){
        Date date = new Date();
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        List<Area_info> list = user_mapper.getActivityTable();
        for(Area_info temp : list){
//          给每一个活跃数据添加  id和年月
            temp.setId(3);
            temp.setYear(Integer.parseInt(year.format(date)));
            temp.setMonth(Integer.parseInt(month.format(date)));
//            保存数据
            area_info_mapper.insert(temp);

        }
    }



    public Result get_all(){

        List<User> list = user_mapper.getall();

        long starttime = System.currentTimeMillis();
        for(User temp: list){
            temp.setLocation(location_in_user_mapper.get_user_locations(temp.getId2()));
        }
        long endtime = System.currentTimeMillis();
//        System.out.println(endtime - starttime);

        if(list.isEmpty()){
            result.setCode(Coding.FAIL);
            result.setData(null);
            result.setMessage("查询失败");
        }else{
            result.setCode(Coding.SUCCESS);
            result.setData(list);
            result.setMessage("查询成功");
        }
        return result;
    }


//分页请求
    public Result get_all(int page, int page_size){
        if(page < 0){
            result.setCode(Coding.FAIL);
            result.setMessage("页码为负数");
            result.setData(null);
            return  result;
        }

        long number = user_mapper.getNumber().longValue() / this.page_size;
        if(page > number){
            result.setCode(Coding.FAIL);
            result.setMessage("页码数超过总页码");
            result.setData(null);
            return  result;
        }

        if(page_size < 0 ){
            result.setCode(Coding.FAIL);
            result.setMessage("当前省情数量为负数");
            result.setData(null);
            return  result;
        }

//      this.page_size在资源文件中更改数值
//        if(page_size > this.page_size){
//            result.setCode(Coding.FAIL);
//            result.setMessage("申请资源数目大于10000");
//            result.setData(null);
//            return  result;
//        }
//设置查询起始   以及查询语句
        int begin = page * page_size;
        List<User> list = user_mapper.getAll(begin, page_size);

// 将location表中的数据  以list方式放入user中
        for(User user : list){
            List l = location_in_user_mapper.get_user_locations(user.getId2());
            user.setLocation(l);
        }

        if(list.isEmpty()){
            result.setCode(Coding.FAIL);
            result.setMessage("数据为空，查询失败");
            result.setData(null);
        }
        else{
            result.setCode(Coding.SUCCESS);
            result.setMessage("获得数据");
            result.setData(list);
        }

        return result;
    }




//    获取用户活跃排行表
//    public Result getActivityTable(){
//
//        return result;
//    }

}
