package com.example.zhihu.mapper.showData;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @data 2021年10月21日20:32
 */
@Mapper
public interface Data_Mapper {

//   根据日期和index_table_key类型（例如，根据age，gender， level）获取数据
    @Select("select * from data_table where index_table_key=#{index_table_key} and year=#{year} and month=#{month}")
    public List getDataByIndex_table_keyAndDate(String index_table_key, int year, int month);

//
//    @Select("select ")

}
