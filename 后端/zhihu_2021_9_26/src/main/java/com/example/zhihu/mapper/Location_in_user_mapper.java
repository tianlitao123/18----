package com.example.zhihu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigInteger;
import java.util.List;

/**
 * @data 2021年10月17日10:06
 */
@Mapper
public interface Location_in_user_mapper {

    @Insert("insert into location_in_user (id2, location) values " +
            "(#{id2}, #{location})")
    public void insert(String id2, String location);


    @Select("select location from location_in_user where id2=#{id2} and location=#{location}")
    public List<String> isSaved(String id2, String location);

//   查询id2的所在地   或者判断当前数据是否在数据库中存在
    @Select("select location from location_in_user where id2=#{id2}")
    public List<String> get_user_locations(String id2);


}
