package com.example.zhihu.tools;

import com.example.zhihu.model.User;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 计算某个用户权重
 * @date 2021/10/12 16:21
 * @param
 * @return null
 */

public class Weight_value {

//    @Autowired
//    private User user;
//
//    public weight_value(User user){
//        this.user = user;
//    }
    private final double follower_weight = 0.5;
    private final double following_count = 0.5;
    private final double answer_weight = 0.24;
    private final double article_weight = 0.24;
    private final double video_weight = 0.14;
    private final double favorite_weight = 0.2;
    private final double question_weight = 0.18;
    private final double columns_weight = 0.18;


/***
 * 根据比重算出每个用户的受欢迎度   后续进行排序
 * @date 2021/10/12 16:15
 * @param user
 * @return double
 */
    public double getWeight_value(User user){

        double result = user.getLevel() * (user.getFollower_count()*this.follower_weight + user.getFollowing_count() * this.following_count)
                * user.getVoteup_count() * (user.getAnswer_count() * this.answer_weight + user.getArticles_count() * this.article_weight +
                user.getZvideo_count() * this.video_weight + user.getFavorite_count() * this.favorite_weight + user.getFollowing_question_count() * this.question_weight
                + user.getColumns_count() * this.columns_weight) ;

        return result;
    }


}
