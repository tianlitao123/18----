package com.example.zhihu.mapper;

import com.example.zhihu.model.User;
import com.example.zhihu.model.User_date;
import org.apache.ibatis.annotations.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @data 2021年10月25日18:49
 * 该user_mapper中含有日期   记录某个用户的历史活动
 *
 */
@Mapper
public interface User_date_mapper {
    @Insert(
            "insert into user_date (id2, url_token, name, gender, level, avatar_url, type, headline, " +
                    "description, business, following_count, follower_count, answer_count, zvideo_count, " +
                    "question_count, articles_count, columns_count, favorite_count, following_question_count, " +
                    "following_topic_count, following_columns_count, following_favlists_count, voteup_count, year," +
                    "month, day) " +
                    "values(#{id2}, #{url_token}, #{name}, #{gender}, #{level}, #{avatar_url}, " +
                    "#{type}, #{headline}, #{description}, #{business}, #{following_count}, #{follower_count}, " +
                    "#{answer_count}, #{zvideo_count}, #{question_count}, #{articles_count}, #{columns_count}, " +
                    "#{favorite_count}, #{following_question_count}, #{following_topic_count}, #{following_columns_count}," +
                    "#{following_favlists_count}, #{voteup_count}, #{year}, #{month}, #{day})")
    @Options(useGeneratedKeys = true, keyProperty = "id1", keyColumn = "id1")
    public void insert(User_date user_date);

//    @Select("select * from `user_date` join location_in_user on user.id1=location_in_user.id1 limit #{begin},#{page_size}")
//    public List<User> getAll(int begin, int page_size);

    //  获取表中数据的个数
    @Select("select count(id1) from user_date")
    public BigInteger getNumber();

//    @Update("truncate table user_date")
//    public void emptyTable();

//    查询某个用户所有信息（按时间排序）
    @Select("select * from user_date where id2=#{id2} order by year, month, day")
    public List<User_date> getUserAndDate(String id2);



}
