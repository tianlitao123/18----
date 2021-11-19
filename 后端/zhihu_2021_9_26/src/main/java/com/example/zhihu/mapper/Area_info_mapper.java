package com.example.zhihu.mapper;

import com.example.zhihu.model.Area_info;
import com.example.zhihu.model.showData.Bangdan;
import com.example.zhihu.model.temp_class.NameAndValue;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.awt.geom.Area;
import java.math.BigInteger;
import java.util.List;

/**
 * @data 2021年10月19日14:17
 */
@Mapper
//@Repository
public interface Area_info_mapper {

    @Select("select * from area_info")
    public List<Area_info> get_all_area_info();


//  判断当前数据是否在数据库已经存在   当前 榜单为id 而且日期相同
    @Select("select * from area_info where id=#{id} and id2=#{id2} and value=#{value} and year=#{year} and month=#{month}")
    public List<Area_info> isExist(Area_info area_info);


//    根据榜单id和日期删除当前信息   此语句为了用于更新数据库
    @Delete("delete from area_info where id=#{id} and year=#{year} and month=#{month}")
    public void deleteByIdYearAndMonth(Area_info area_info);


//    @Select("select * from area_info where id=#{id}")
//    public List<Area_info> getAreaInfoById(int id);

//  根据id查询榜单用户信息（名称，value）按value从大到小排序  通过List<bangdan>类返回
    @Select("select distinct a.id2, u.name,u.url_token,u.avatar_url,a.`value`, a.year, a.month from area_info a join user u  using(id2) where a.id=#{id} and a.year=#{year} and a.month=#{month} ORDER BY value desc ")
    public List<Bangdan> getUsersInfoByAreasId(int id, int year, int month);


    @Insert("insert into area_info values(#{id}, #{id2}, #{value}, #{year}, #{month})")
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public void insert(Area_info area_info);

//    获取某年某月的榜单所有信息
    @Select("select a.id2, u.name,u.url_token,u.avatar_url,a.`value`, a.year, a.month from area_info a join user_check u on a.id2=u.id2 where a.year=#{year} and a.month=#{month}")
    public List<Bangdan> getAllListInfo(int year, int month);

}
