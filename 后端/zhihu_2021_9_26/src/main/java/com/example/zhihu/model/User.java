package com.example.zhihu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @data 2021年09月26日16:02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

//  与id2相互对应，唯一的数字型id
    private Integer id1;
//  用户唯一标识
    private String id2;
//  token
    private String url_token;
//  用户昵称
    private String name;
//  用户性别
    private String gender;
//  用户等级
    private int level;
//  用户头像
    private String avatar_url;
//    用户类型
    private String type;
//    用户头文字（用户名称后的文字）
    private String headline;
//    个人简介
    private String description;
//    所在行业
    private String business;
//    关注度
    private double following_count;
//    粉丝数
    private double follower_count;
//    提问数
    private double question_count;
//    回答数量
    private double answer_count;
//    文章数
    private double articles_count;
//    视频数
    private double zvideo_count;
//    专栏数
    private double columns_count;
//    收藏数
    private double favorite_count;
//    关注问题的数量
    private double following_question_count;
//    关注的话题数量
    private double following_topic_count;
//    关注的专栏数量
    private double following_columns_count;
//    关注的文件夹数量
    private double following_favlists_count;
//    居住地
    private List<String> location;
//    被赞同次数
    private double voteup_count;


    


}
