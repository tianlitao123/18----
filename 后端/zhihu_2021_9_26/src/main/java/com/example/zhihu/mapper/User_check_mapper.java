package com.example.zhihu.mapper;

import com.example.zhihu.model.Area_info;
import com.example.zhihu.model.User;
import com.example.zhihu.model.User_check;
import com.example.zhihu.model.temp_class.NameAndFrequency;
import org.apache.ibatis.annotations.*;

import javax.naming.Name;
import java.math.BigInteger;
import java.util.List;

/**
 * @data 2021年10月21日22:04
 */
@Mapper
public interface User_check_mapper {

    /**
     * 将传入参数改为user类型    一下为备份数据；
     * String id2, String url_token, String name, String gender, int level_info_level,
     *                        String avatar_url, String type, String headline, String description, String business_name,
     *                        double following_count, double follower_count, double answer_count, double zvideo_count,
     *                        double question_count, double articles_count, double columns_count, double favorite_count,
     *                        double following_question_count, double following_topic_count, double following_column_count,
     *                        double following_favilists_count, double voteup_count, int frequency
     * @date 2021/10/18 14:43
     * @param user_check
     * @return int
     */
//这里删除location字段，因为location为list类型，需要新建表
    @Insert(
            "insert into user_check (id2, url_token, name, gender, level, avatar_url, type, headline," +
                    "description, business, following_count, follower_count, answer_count, zvideo_count, " +
                    "question_count, articles_count, columns_count, favorite_count, following_question_count," +
                    "following_topic_count, following_columns_count, following_favlists_count, voteup_count, frequency) " +
                    "values(#{id2}, #{url_token}, #{name}, #{gender}, #{level}, #{avatar_url}, " +
                    "#{type}, #{headline}, #{description}, #{business}, #{following_count}, #{follower_count}, " +
                    "#{answer_count}, #{zvideo_count}, #{question_count}, #{articles_count}, #{columns_count}, " +
                    "#{favorite_count}, #{following_question_count}, #{following_topic_count}, #{following_columns_count}," +
                    "#{following_favlists_count}, #{voteup_count}, #{frequency})")
    @Options(useGeneratedKeys = true, keyProperty = "id1", keyColumn = "id1")
    public void insert(User_check user_check);

//    @Select("select * from `user_check` join location_in_user on user.id1=location_in_user.id1 limit #{begin},#{page_size}")
//    public List<User_check> getAll(int begin, int page_size);

    //  获取表中数据的个数
    @Select("select count(id1) from user_check")
    public Integer getUsersNum();
//查询某个用户的频率   判断某个用户是否存在
    @Select("select * from user_check where id2=#{id2}")
    public List<User_check> getFrequencyById2(String id2);



//    清空表中的内容
    @Update("truncate table user_check")
    public void emptyTable();
//获取frequency排序的用户名和frequency
    @Select("Select id2, frequency value from user_check order by frequency desc limit 20")
    public List<Area_info> getFrequency();
//更新frequency
    @Update("update user_check set frequency=#{frequency} where id2=#{id2}")
    public void updateFrequency(String id2, int frequency);
//判断frequency是否更新成功
    @Select("select * from user_check where id2=#{id2} and frequency=#{frequency}")
    public List<User_check> isUpdate(String id2, int frequency);

//  判断是否存在该用户
    @Select("select * from user_check where id2=#{id2}")
    public List<User_check> isExistUser(String id2);
//根据id查询用户
    @Select("select * from user_check where id2=#{id2}")
    public List<User_check> getUserById2(String id2);

    @Select("select * from user_check")
    public List<User_check> getAllUser();

}
