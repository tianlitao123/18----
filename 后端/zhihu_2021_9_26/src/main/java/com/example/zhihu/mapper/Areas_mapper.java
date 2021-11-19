package com.example.zhihu.mapper;

import com.example.zhihu.model.Areas;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @data 2021年10月19日14:11
 */
@Mapper
public interface Areas_mapper {

    @Select("select * from areas")
    public List<Areas> getAllAreas();

    @Select("select * from areas where id=#{id}")
    public List<Areas> getAreasById(int id);

    @Select("select * from areas where name=#{name}")
    public Integer getAreasByName(String name);

    @Select("select formula from areas where id=#{id}")
    public String getFormulaById(int id);


    @Insert("insert into areas (name, formula) values (#{name}, #{formula})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public void insert(Areas areas);

}
