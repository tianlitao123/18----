package com.example.zhihu.service;

import com.example.zhihu.mapper.Area_info_mapper;
import com.example.zhihu.mapper.Location_in_user_mapper;
import com.example.zhihu.mapper.User_check_mapper;
import com.example.zhihu.model.Area_info;
import com.example.zhihu.model.Result;
import com.example.zhihu.model.User_check;

import com.example.zhihu.tools.GetTime;
import com.example.zhihu.tools.enums.Coding;
import lombok.SneakyThrows;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @data 2021年10月25日19:36
 */
@Service
public class User_check_service {

    @Autowired
    private User_check user_check;

    @Autowired
    private User_check_mapper user_check_mapper;

    @Autowired
    private Location_in_user_mapper location_in_user_mapper;

    @Autowired
    private Location_in_user_service location_in_user_service;

    @Autowired
    Area_info_mapper area_info_mapper;

    @Autowired
    Result result;

    @Autowired
    GetTime getTime;

    @Autowired
            User_date_service user_date_service;

//    公平锁
    Lock lock = new ReentrantLock(true);


    public Result insert(List<User_check> list){
        System.out.println("执行操作====");


//   因为该表中信息会随时更替 且 信息唯一  所以首先清空表
//        user_check_mapper.emptyTable();
        for(User_check user_check: list) {
//          当用户不存在
            if (user_check_mapper.isExistUser(user_check.getId2()).isEmpty()) {
//                设置初始频率为1
                user_check.setFrequency(1);
                user_check_mapper.insert(user_check);

                String id2 = user_check.getId2();
//              爬虫爬到的user存储到user——check中时  也要存储到user——date
//                user_date_service.insert(user_check);
                user_date_service.insertFromSearch(user_check);

//              存储用户位置   如果当前用户所在地在location_in_user中
                if(location_in_user_mapper.get_user_locations(id2).isEmpty()){
                    if (user_check.getLocation().size() > 0) {
//                BigInteger id1 = user_mapper.get_id1(user.getId2());
                        for(String location : user_check.getLocation()) {
//                    判断当前用户的地理位置是否已经再数据库中存在   若不存在 则保存
                            if(location_in_user_mapper.isSaved(id2, location).isEmpty()){
                                location_in_user_mapper.insert(id2, location);
                            }
                            else{
                                continue;
                            }
                        }
                    }

                }


//              判断数据是否存储    存储失败
                if (user_check_mapper.getFrequencyById2(user_check.getId2()) == null) {
                    result.setMessage("数据存储失败");
                    result.setCode(Coding.INSERT_FAIL);
                    result.setData(null);
                    return result;
                }
//              存储成功
//                else {
//                    result.setCode(Coding.INSERT_SUCCESS);
//                    result.setMessage("存储成功");
//                    result.setData(null);
//                }
            }
//          用户已存在
            else {
//               获取当前用户的被访问频率
                int frequency = user_check_mapper.getFrequencyById2(user_check.getId2()).get(0).getFrequency();

                user_check_mapper.updateFrequency(user_check.getId2(),  frequency + 1);
//                更新失败
                if (user_check_mapper.isUpdate(user_check.getId2(), frequency + 1).isEmpty()) {
                    result.setCode(Coding.INSERT_FAIL);
                    result.setMessage("该数据更新失败：id" + user_check.getId2() + "  frequency:" + user_check.getFrequency());
                    result.setData(null);
                    return result;
                }
            }
        }
        result.setCode(Coding.INSERT_SUCCESS);
        result.setMessage("存储成功");
        result.setData(null);
//        for(User_check user_check:list){
//            User_check user_check1 = user_check_mapper.isUpdate(user_check.getId2(), user_check.getFrequency());
//            if(user_check1 == null){
//
//            }
//
//
//            user_check_mapper.insert(user_check);
//            if(user_check.getId()== null){
//                result.setData(null);
//                result.setCode(Coding.INSERT_FAIL);
//                result.setMessage("该数据存储失败：" + user_check.toString());
//                return result;
//            }
//        }
//        result.setMessage("所有数据存储成功");
//        result.setCode(Coding.INSERT_SUCCESS);
//        result.setData(null);

//      将用户频率榜添加到area_info中
        List<Area_info> list_area_info = user_check_mapper.getFrequency();

        Area_info temp = new Area_info(4, null, null, getTime.getYear(), getTime.getMonth());
        area_info_mapper.deleteByIdYearAndMonth(temp);
        for(Area_info area_info:list_area_info){
            area_info.setId(4);
            area_info.setYear(getTime.getYear());
            area_info.setMonth(getTime.getMonth());
            area_info_mapper.insert(area_info);
        }
        return result;
    }

    public Result updateFrequency(String id2, int frequency){
//        User_check user_check = user_check_mapper.getFrequencyById2(id2);
//        if(user_check == null){
//            user_check_mapper.insert();
//        }
        user_check_mapper.updateFrequency(id2, frequency);
        if(user_check_mapper.isUpdate(id2, frequency) == null){
            result.setCode(Coding.INSERT_FAIL);
            result.setMessage("该数据更新失败：id" + id2 + "  frequency:" + frequency);
            result.setData(null);
        }else{
            result.setMessage("数据更新成功");
            result.setData(null);
            result.setCode(Coding.INSERT_SUCCESS);
        }
        return result;
    }

    public Result getFrequencyById2(String id2){


        List<User_check> list = user_check_mapper.getFrequencyById2(id2);
        if(list.isEmpty()){
            result.setCode(Coding.FAIL);
            result.setMessage("不存在该用户 id：" + id2);
            result.setData(null);
        }
        else{
            if(user_check.getFrequency() == null){
                result.setCode(Coding.FAIL);
                result.setData(user_check.getFrequency());
                result.setMessage("该用户最近不活跃,频率为0 id：" + id2);
                return result;
            }

            result.setCode(Coding.SUCCESS);
            result.setMessage("查询成功");
            result.setData(user_check.getFrequency());
        }
        return result;
    }

    public Result getFrequencyList(){
        List list = user_check_mapper.getFrequency();
        if(list.size() == 0){
            result.setCode(Coding.FAIL);
            result.setMessage("查询失败");
            result.setData(null);
        }
        else{
            result.setCode(Coding.SUCCESS);
            result.setMessage("查询成功");
            result.setData(list);
        }
        return result;
    }


//    根据id2获取user_check中的用户数据
    public Result getUserById2(String id2){
        List<User_check> list = user_check_mapper.getUserById2(id2);
        for(User_check user_check:list){
            user_check.setLocation(location_in_user_mapper.get_user_locations(user_check.getId2()));
        }
        if(list.isEmpty()){
            result.setCode(Coding.FAIL);
            result.setData(null);
            result.setMessage("查询失败，为查询到id2：" + id2);
        }else{
            result.setCode(Coding.SUCCESS);
            result.setData(list);
            result.setMessage("查询成功");
        }

        return result;
    }
//获取所有用户数据
    public Result getAllUser(){
        List<User_check> list = user_check_mapper.getAllUser();
        for(User_check temp: list){
            temp.setLocation(location_in_user_mapper.get_user_locations(temp.getId2()));
        }

        if(list.isEmpty()){
            result.setCode(Coding.FAIL);
            result.setData(null);
            result.setMessage("查询失败");
        }else{
            result.setCode(Coding.SUCCESS);
            result.setData(list);
            result.setMessage("查询成功");
        }
        return result;
    }

//搜索该id的用户
    @SneakyThrows
    public  Result searchUser(String id2, Integer frequency){
//        System.out.println("开始获取用户信息");

        lock.lock();

//        System.out.println("加锁");
        //      判断当前是否有锁
//        if(lock.tryLock()){
//            TimeUnit.MILLISECONDS.sleep(1000);
//            System.out.println("拿到锁");
//        }else{
//            System.out.println("未拿到锁");
//        }
//        对程序上锁



        List<User_check> list = user_check_mapper.getUserById2(id2);

////      判断当前是否拿到锁
//        if(lock.tryLock()){
//如果查询当前用户不存在
            if(list.isEmpty()){
                int time = 0;
                while(list.isEmpty()){
                    Thread.sleep(1000);
//                    System.out.println("2");
                    time = time + 1;
                    list = user_check_mapper.getUserById2(id2);
//              查询超时   判断时间为两秒的时候   程序报错 返回
                    if(time == 20){
                        result.setCode(Coding.FAIL);
                        result.setData(null);
                        result.setMessage("查询超时，请稍后再试");

//                    程序超时   返回值并且解锁
                        lock.unlock();
                        return  result;
                    }
                }
            }
//        用户存在
            else{
//                Integer frequency = user_check_mapper.getUserById2(id2).get(0).getFrequency();
                System.out.println( "初始：" + frequency);
//            查询用户时间超过某一个值  则返回错误
                int time = 0;
                while (true){
                    Thread.sleep(1000);
//                    int new_frequency = user_check_mapper.getUserById2(id2).get(0).getFrequency();
//                    System.out.println("插入后：" + new_frequency);
                    if(user_check_mapper.getFrequencyById2(id2).get(0).getFrequency() != frequency){
                        list = user_check_mapper.getUserById2(id2);
                        break;
                    }

//                    System.out.println("3");
                    time = time + 1;
//                    list = user_check_mapper.getUserById2(id2);
//                查询超时   t==3/2秒时  退出程序
                    if(time == 20){
                        result.setCode(Coding.FAIL);
                        result.setData(null);
                        result.setMessage("查询超时1，请稍后再试");

//            程序超时   返回值并且解锁
                        lock.unlock();
                        return result;
                    }
                }
            }

//        }else{
//            TimeUnit.MILLISECONDS.sleep(1000);
//            System.out.println("1");
//        }

        result.setCode(Coding.SUCCESS);
        result.setMessage("查询成功");
        result.setData(list);
        lock.unlock();
        return result;
    }

}
