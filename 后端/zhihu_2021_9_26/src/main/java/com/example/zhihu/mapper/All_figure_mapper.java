package com.example.zhihu.mapper;

import com.example.zhihu.model.All_figure;
import com.example.zhihu.model.User_draw;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

/**
 * @data 2021年10月28日18:52
 */

@Mapper
public interface All_figure_mapper {

    public void insert_superTable(String name);
//获取表明相应的主键id   或者验证当前表明是否存在super——table中
    public Integer get_idByTableName(String name);

//数据表
    public void insert_userDraw(User_draw user_draw);

    public List<User_draw> is_saved(User_draw user_draw);

//该处的list泛型为 All_figure
//    public Result get_userDrawByYear(All_figure all_figure);
////该处的list泛型为 All_figure
//    public Result get_userDrawByYearAndMonth(All_figure all_figure);
//获取所有时间线上该类型数据
    public List<All_figure> get_userDraw1(All_figure all_figure);

    public List<All_figure> get_userDraw2(All_figure all_figure);

    public List<All_figure> get_userDraw3(All_figure all_figure);

//    获取最后一条数据的id
    public Integer getFinalId();

//    public List<All_figure> get_userDraw1By

    public void insert_userDraw_many(List<User_draw> list);

}
