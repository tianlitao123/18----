package com.example.zhihu.service;

import com.example.zhihu.mapper.Location_in_user_mapper;
import com.example.zhihu.mapper.User_date_mapper;
import com.example.zhihu.model.Result;
import com.example.zhihu.model.User;
import com.example.zhihu.model.User_check;
import com.example.zhihu.model.User_date;
import com.example.zhihu.tools.GetTime;
import com.example.zhihu.tools.enums.Coding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @data 2021年10月25日21:31
 */
@Service
public class User_date_service {
    @Autowired
    private User_date_mapper user_date_mapper;

    @Autowired
    private User_date user_date;

    @Autowired
    private Location_in_user_mapper location_in_user_mapper;

    @Autowired
    private Result result;

    @Autowired
    private GetTime getTime;

    public Result getUserAndDate(String id2){
        List<User_date> list = user_date_mapper.getUserAndDate(id2);

        // 将location表中的数据  以list方式放入user中
        for(User_date user_date : list){
//            if(!location_in_user_mapper.get_user_locations(id2).isEmpty()){
                user_date.setLocation(location_in_user_mapper.get_user_locations(id2));
//            }

        }

        if(list.isEmpty()){
            result.setData(list);
            result.setCode(Coding.FAIL);
            result.setMessage("未查询到该用户id：" + id2);
        }else{
            result.setMessage("查询成功");
            result.setCode(Coding.SUCCESS);
            result.setData(list);
        }
        return result;
    }

    public Result insert(List<User_date> list){
        if(list.size() == 0){
            result.setMessage("未接收到任何数据");
            result.setCode(Coding.INSERT_SUCCESS);
            result.setData(null);
        }else{
            for(User_date user_date : list){
                user_date_mapper.insert(user_date);
                if(user_date.getId1() ==null){
                    result.setData(null);
                    result.setCode(Coding.INSERT_FAIL);
                    result.setMessage("数据存储失败：" + user_date.toString());
                    return result;
                }
            }
            result.setMessage("数据存储成功");
            result.setData(null);
            result.setCode(Coding.INSERT_SUCCESS);
        }
        return result;
    }


    public void insertFromSearch(User_check user_check){
        if(user_date_mapper.getUserAndDate(user_check.getId2()).isEmpty()){
            user_date.setId2(user_check.getId2());
            user_date.setUrl_token(user_check.getUrl_token());
            user_date.setName(user_check.getName());
            user_date.setGender(user_check.getGender());
            user_date.setLevel(user_check.getLevel());
            user_date.setAvatar_url(user_check.getAvatar_url());
            user_date.setType(user_check.getType());
            user_date.setHeadline(user_check.getHeadline());
            user_date.setDescription(user_check.getDescription());
            user_date.setBusiness(user_check.getBusiness());
            user_date.setFollower_count(user_check.getFollower_count());
            user_date.setFollowing_count(user_check.getFollowing_count());
            user_date.setAnswer_count(user_check.getAnswer_count());
            user_date.setZvideo_count(user_check.getZvideo_count());
            user_date.setQuestion_count(user_check.getQuestion_count());
            user_date.setArticles_count(user_check.getArticles_count());
            user_date.setColumns_count(user_check.getColumns_count());
            user_date.setFavorite_count(user_check.getFavorite_count());
            user_date.setFollowing_question_count(user_check.getFollowing_question_count());
            user_date.setFollowing_topic_count(user_check.getFollowing_topic_count());
            user_date.setFollowing_columns_count(user_check.getFollowing_columns_count());
            user_date.setFollowing_favlists_count(user_check.getFollowing_favlists_count());
            user_date.setVoteup_count(user_check.getVoteup_count());
            user_date.setYear(getTime.getYear());
            user_date.setMonth(getTime.getMonth());
            user_date.setDay(getTime.getDay());

            user_date_mapper.insert(user_date);
        }
    }
}
