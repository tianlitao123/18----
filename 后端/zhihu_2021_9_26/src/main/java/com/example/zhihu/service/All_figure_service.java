package com.example.zhihu.service;

import com.example.zhihu.mapper.All_figure_mapper;
import com.example.zhihu.model.All_figure;
import com.example.zhihu.model.Result;
import com.example.zhihu.model.User_draw;
import com.example.zhihu.tools.enums.Coding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * @data 2021年11月01日12:52
 */
@Service
public class All_figure_service {

    @Autowired
    private Result result;

    @Autowired
    private All_figure_mapper all_figure_mapper;

    @Autowired
    private User_draw user_draw;

/**
 * 取消super_table 该表功能取消  主要使用user_draw
 *
 * @date 2021/11/9 10:57
 * @param null
 * @return null
 */
//    public Result insert_superTable(String name){
//        Integer id = all_figure_mapper.get_idByTableName(name);
////      如果当前表中不存在该表名   则插入super表中
//        if(id == null){
//            all_figure_mapper.insert_superTable(name);
////            验证是否已经存储表中
//            if(all_figure_mapper.get_idByTableName(name) == null){
//                result.setMessage("数据存储失败name：" + name);
//                result.setCode(Coding.INSERT_FAIL);
//                result.setData(null);
//            }else{
//                result.setMessage("存储成功");
//                result.setCode(Coding.INSERT_SUCCESS);
//                result.setData(null);
//            }
//        }else{
//            result.setData(null);
//            result.setCode(Coding.INSERT_FAIL);
//            result.setMessage("已存在该数据");
//        }
//        return result;
//    }

/***
 *
 * @date 2021/11/1 14:09
 * @param list 类型为All_list   存储时需要转化为user_draw
 *
 * @return com.example.zhihu.model.Result
 */
    public Result insert_userDraw(List<All_figure> list){
//        当前表名的id
//        Integer id = all_figure_mapper.get_idByTableName(name);
//       如果不存在表名   先插入表名  然后获取id
//        if(id == null){
//            all_figure_mapper.insert_superTable(name);
//            id=all_figure_mapper.get_idByTableName(name);
//        }else{
//            id=all_figure_mapper.get_idByTableName(name);
//        }




        for(All_figure all_figure: list){
//            获取该表中最后i个数据的id
            Integer id = all_figure_mapper.getFinalId();
//            如果数据库为空则返回null
            if(id == null){
                id = 0;
            }

            if(all_figure.getKey2() == null){
                user_draw.setId(id + 1);
                user_draw.setKey(all_figure.getKey1());
                user_draw.setKey_value(all_figure.getKey_value1());
                user_draw.setNum(all_figure.getNum());
                user_draw.setYear(all_figure.getYear());
                user_draw.setMonth(all_figure.getMonth());
//                存储
                all_figure_mapper.insert_userDraw(user_draw);
                result = is_insert_userDraw(user_draw);
            }else if(all_figure.getKey3() == null){
                user_draw.setId(id + 1);
                user_draw.setKey(all_figure.getKey1());
                user_draw.setKey_value(all_figure.getKey_value1());
                user_draw.setNum(all_figure.getNum());
                user_draw.setYear(all_figure.getYear());
                user_draw.setMonth(all_figure.getMonth());

                all_figure_mapper.insert_userDraw(user_draw);
                result = is_insert_userDraw(user_draw);

                user_draw.setId(id + 1);
                user_draw.setKey(all_figure.getKey2());
                user_draw.setKey_value(all_figure.getKey_value2());
                user_draw.setNum(all_figure.getNum());
                user_draw.setYear(all_figure.getYear());
                user_draw.setMonth(all_figure.getMonth());

                all_figure_mapper.insert_userDraw(user_draw);
                result = is_insert_userDraw(user_draw);
            }else{
                user_draw.setId(id + 1);
                user_draw.setKey(all_figure.getKey1());
                user_draw.setKey_value(all_figure.getKey_value1());
                user_draw.setNum(all_figure.getNum());
                user_draw.setYear(all_figure.getYear());
                user_draw.setMonth(all_figure.getMonth());

                all_figure_mapper.insert_userDraw(user_draw);
                result = is_insert_userDraw(user_draw);

                user_draw.setId(id + 1);
                user_draw.setKey(all_figure.getKey2());
                user_draw.setKey_value(all_figure.getKey_value2());
                user_draw.setNum(all_figure.getNum());
                user_draw.setYear(all_figure.getYear());
                user_draw.setMonth(all_figure.getMonth());

                all_figure_mapper.insert_userDraw(user_draw);
                result = is_insert_userDraw(user_draw);

                user_draw.setId(id + 1);
                user_draw.setKey(all_figure.getKey3());
                user_draw.setKey_value(all_figure.getKey_value3());
                user_draw.setNum(all_figure.getNum());
                user_draw.setYear(all_figure.getYear());
                user_draw.setMonth(all_figure.getMonth());

                all_figure_mapper.insert_userDraw(user_draw);
                result = is_insert_userDraw(user_draw);
            }
            if(result.getCode() == 450){
                return result;
            }

        }
        result.setMessage("数据存储成功");
        result.setCode(Coding.INSERT_SUCCESS);
        result.setData(null);

        return result;
    }
//判断user_draw是否存储失败   若失败直接
    public Result is_insert_userDraw(User_draw user_draw){
        List list = all_figure_mapper.is_saved(user_draw);
        if(list.isEmpty()){
            result.setMessage("数据存储失败 :" + user_draw.toString());
            result.setCode(Coding.INSERT_FAIL);
            result.setData(list);
        }
//        else{
//            result.setMessage("数据存储成功");
//            result.setCode(Coding.INSERT_SUCCESS);
//            result.setData(null);
//        }
        return result;
    }

//查询相关数据   例如sex和level 相关的数据
    public Result get_userDraw(All_figure all_figure){

        if(all_figure.getKey2() == null){
            return get_userDraw_new(all_figure_mapper.get_userDraw1(all_figure));

        }else if(all_figure.getKey3() == null){
            return get_userDraw_new(all_figure_mapper.get_userDraw2(all_figure));
        }else{
            return get_userDraw_new(all_figure_mapper.get_userDraw3(all_figure));
        }

//        return result;
    }
//对上上面的get_userDraw方法进行分装  返回result数据
    public Result get_userDraw_new(List list){
        if(list.isEmpty()){
            result.setData(null);
            result.setCode(Coding.FAIL);
            result.setMessage("查询失败 未查询到相关数据");
        }else{
            result.setData(list);
            result.setCode(Coding.SUCCESS);
            result.setMessage("查询成功");
        }
        return  result;
    }


//  根据时间返回顺序返回图表数据
//    public Result get_userDrawByTime(List list){
//
//    }
//    static Integer id;
    //    批量插入
    public void insert_userDaraw1_many(List<All_figure> list){
        //        临时添加list   批量添加至数据库
        List<User_draw> new_list = new ArrayList<>();
        Integer id = all_figure_mapper.getFinalId();
        for(All_figure all_figure: list){
//            获取该表中最后i个数据的id

//            如果数据库为空则返回null
            if(id == null){
                id = 0;
            }
            id++;
            if(all_figure.getKey2() == null){

                User_draw user_draw1 = new User_draw();
                user_draw1.setId(id);
                user_draw1.setKey(all_figure.getKey1());
                user_draw1.setKey_value(all_figure.getKey_value1());
                user_draw1.setNum(all_figure.getNum());
                user_draw1.setYear(all_figure.getYear());
                user_draw1.setMonth(all_figure.getMonth());
                new_list.add(user_draw1);

            }else if(all_figure.getKey3() == null){
                User_draw user_draw1 = new User_draw();
                user_draw1.setId(id);
                user_draw1.setKey(all_figure.getKey1());
                user_draw1.setKey_value(all_figure.getKey_value1());
                user_draw1.setNum(all_figure.getNum());
                user_draw1.setYear(all_figure.getYear());
                user_draw1.setMonth(all_figure.getMonth());

                new_list.add(user_draw1);

                User_draw user_draw2 = new User_draw();
                user_draw2.setId(id);
                user_draw2.setKey(all_figure.getKey2());
                user_draw2.setKey_value(all_figure.getKey_value2());
                user_draw2.setNum(all_figure.getNum());
                user_draw2.setYear(all_figure.getYear());
                user_draw2.setMonth(all_figure.getMonth());

                new_list.add(user_draw2);
            }else{

                user_draw.setId(id);
                user_draw.setKey(all_figure.getKey1());
                user_draw.setKey_value(all_figure.getKey_value1());
                user_draw.setNum(all_figure.getNum());
                user_draw.setYear(all_figure.getYear());
                user_draw.setMonth(all_figure.getMonth());

                new_list.add(user_draw);

                user_draw.setId(id);
                user_draw.setKey(all_figure.getKey2());
                user_draw.setKey_value(all_figure.getKey_value2());
                user_draw.setNum(all_figure.getNum());
                user_draw.setYear(all_figure.getYear());
                user_draw.setMonth(all_figure.getMonth());

                new_list.add(user_draw);

                user_draw.setId(id);
                user_draw.setKey(all_figure.getKey3());
                user_draw.setKey_value(all_figure.getKey_value3());
                user_draw.setNum(all_figure.getNum());
                user_draw.setYear(all_figure.getYear());
                user_draw.setMonth(all_figure.getMonth());

                new_list.add(user_draw);
            }


        }



        all_figure_mapper.insert_userDraw_many(new_list);
    }


}
