package com.example.zhihu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @data 2021年10月19日8:58
 *
 * 所有地点和及其code存储
 */


@Mapper
public interface Location_mapper {

    @Insert("insert into locations (name) values(#{name})")
    public void insert(String name);

    @Select("select location_code from locations where name=#{name}")
    public Integer get_location_code(String name);

    @Select("select name from locations where location_code=#{location_code}")
    public String get_location_name(int location_code);
}
