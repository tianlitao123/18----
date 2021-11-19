package com.example.zhihu.tools;



import com.example.zhihu.service.Location_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @data 2021年10月19日9:33
 *
 * 读取txt文件，将所有地名存入数据库
 */
@Component
public class Insert_locations {
    @Autowired
    Location_service location_service;

    List<String> list = new ArrayList<String>();
//    int i = 0;
    String s ;
    public  void insert_location_data() {
        FileReader fr;
        {
            try {
                fr = new FileReader("D:\\province_list.txt");
                BufferedReader bf = new BufferedReader(fr);
                while ((s = bf.readLine()) != null) {

                    String temp = s.substring(0, s.indexOf('：'));
//                    list.add(temp);
//                    System.out.println(temp);
                    location_service.insert(temp);
                }
                bf.close();
                fr.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//
//        for(String s : list){
//            System.out.println(s);
//            location_service.insert(i++, s);
//        }

    }
}
