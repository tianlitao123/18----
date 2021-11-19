package com.example.zhihu.mapper;


import com.example.zhihu.model.Area_info;
import com.example.zhihu.model.User;
import com.example.zhihu.model.showData.Bangdan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.math.BigInteger;
import java.util.List;

/**
 * @data 2021年10月17日1:03
 */
@Mapper
public interface User_mapper {


    /**
     * 将传入参数改为user类型    一下为备份数据；
     * String id2, String url_token, String name, String gender, int level_info_level,
     *                        String avatar_url, String type, String headline, String description, String business_name,
     *                        double following_count, double follower_count, double answer_count, double zvideo_count,
     *                        double question_count, double articles_count, double columns_count, double favorite_count,
     *                        double following_question_count, double following_topic_count, double following_column_count,
     *                        double following_favilists_count, double voteup_count
     * @date 2021/10/18 14:43
     * @param user
     * @return int
     */
//这里删除location字段，因为location为list类型，需要新建表
    @Insert(
        {"<script>",
            "insert into user " +
                    "(id2, url_token, name, gender, level, avatar_url, type, headline," +
                        "description, business, following_count, follower_count, answer_count, zvideo_count, " +
                        "question_count, articles_count, columns_count, favorite_count, following_question_count," +
                        "following_topic_count, following_columns_count, following_favlists_count, voteup_count) " +
            "values",
         "<foreach collection='list' item='item' index='index' separator=','>",
                    "(#{item.id2}, #{item.url_token}, #{item.name}, #{item.gender}, #{item.level}, #{item.avatar_url}, " +
                "#{item.type}, #{item.headline}, #{item.description}, #{item.business}, #{item.following_count}, #{item.follower_count}, " +
                "#{item.answer_count}, #{item.zvideo_count}, #{item.question_count}, #{item.articles_count}, #{item.columns_count}, " +
                "#{item.favorite_count}, #{item.following_question_count}, #{item.following_topic_count}, #{item.following_columns_count}," +
                "#{item.following_favlists_count}, #{item.voteup_count})",
          "</foreach>",
        "</script>"}
    )
    @Options(useGeneratedKeys = true, keyProperty = "id1", keyColumn = "id1")
    public void insert(List list);

//    @Select("select * from `user` join location_in_user on user.id1=location_in_user.id1 limit #{begin},#{page_size}")
    @Select("select * from `user` limit #{begin},#{page_size}")
    public List<User> getAll(int begin, int page_size);

    @Select("select * from `user`")
    public List<User> getall();

//  获取表中数据的个数
    @Select("select count(id1) from user")
    public BigInteger getNumber();
//    /***
//     * 根据id2查询id   用于插入地址表时使用
//     * @date 2021/10/18 9:27
//     * @param id2
//     * @return java.math.BigInteger
//     */
//    @Select("Select id1 from user where id2=#{id2}")
//    public BigInteger get_id1(String id2);

//    获取活跃榜单前20   每月保存user数据时调用   将活跃榜单数据分析出来 保存至数据库中
    @Select("SELECT id2, count(id2) value from user GROUP BY id2  order by count(id2) desc limit 20")
    public List<Area_info> getActivityTable();

}
