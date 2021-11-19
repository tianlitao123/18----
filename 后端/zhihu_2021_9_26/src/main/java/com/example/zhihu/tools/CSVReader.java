package com.example.zhihu.tools;



import com.example.zhihu.model.All_figure;
import com.example.zhihu.model.Area_info;
import com.example.zhihu.model.User;
import com.example.zhihu.model.User_check;
import com.example.zhihu.model.showData.Bangdan;
import com.example.zhihu.service.All_figure_service;
import com.example.zhihu.service.Area_info_service;
import com.example.zhihu.service.User_check_service;
import com.example.zhihu.service.User_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CSVReader {

//    @Autowired
//    All_figure all_figure;

    @Autowired
    All_figure_service all_figure_service;

    @Autowired
    GetTime getTime;

//    插入双属性数据
    public void readCSV(List<String> file_list) throws FileNotFoundException, UnsupportedEncodingException {

//        临时

//        List<String>  new_file_list = new ArrayList<>();
//
//            try {
//                File file = new File("D:\\新建文本文档.txt");
//                InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
//                BufferedReader bf = new BufferedReader(inputReader);
//                String temp = "";
//                while(true) {
//                    if (((temp = bf.readLine()) != null)){
//                        new_file_list.add(temp);
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }



        for(String temp_name : file_list) {
//            System.out.println("start" + getTime.getTime() + temp_name);

            String[] two_name = temp_name.split("-");
            String name1= temp_name;
//            文件名转置
            String name2= two_name[1] + "-" + two_name[0];

            File csv = new File("D:\\binary\\"+ temp_name +".csv");
            InputStreamReader isr = new InputStreamReader(new FileInputStream(csv), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
//       存储csv数据
            List<All_figure> list = new ArrayList<>();
            try {
                line = br.readLine();
                line = br.readLine();
                while (line != null) {
                    All_figure all_figure = new All_figure();

                    String[] temp = line.split(",");

//                System.out.println(temp[0] + "  " + temp[1] +" " +   temp[2]);
                    all_figure.setKey1(name1);
                    all_figure.setKey_value1(temp[0]);
                    all_figure.setKey2(name2);
                    all_figure.setKey_value2(temp[1]);
                    all_figure.setNum(Integer.parseInt(temp[2]));
                    all_figure.setYear(getTime.getYear());
                    all_figure.setMonth(getTime.getMonth());

                    list.add(all_figure);
                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

//单条插入数据   贼慢
//            all_figure_service.insert_userDraw(list);
// 多条数据插入
            all_figure_service.insert_userDaraw1_many(list);
//            System.out.println("end" + getTime.getTime() + temp_name);
        }
    }

//插入单属性数据
    public void readCSV2() throws FileNotFoundException {
        File file = new File("D:\\single");
        String[] filePath = file.list();
        for(String temp : filePath){
            File csv = new File("D:\\single\\"+ temp);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(csv), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
//       存储csv数据
            List<All_figure> list = new ArrayList<>();
            try {
                line = br.readLine();
                line = br.readLine();
                while (line != null) {
                    All_figure all_figure = new All_figure();

                    String[] tt = line.split(",");

//                System.out.println(temp[0] + "  " + temp[1] +" " +   temp[2]);
                    all_figure.setKey1(temp.substring(0, temp.indexOf('.')));
                    all_figure.setKey_value1(tt[1]);
                    all_figure.setNum(Integer.parseInt(tt[2]));
                    all_figure.setYear(getTime.getYear());
                    all_figure.setMonth(getTime.getMonth());

                    list.add(all_figure);
                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            all_figure_service.insert_userDaraw1_many(list);

        }
    }

    @Autowired
    User user;

    @Autowired
    User_service user_service;

    @Autowired
    User_check_service user_check_service;

    public  void insert_user_check() throws IOException {
        List<User_check> list = new ArrayList<>();


        File csv = new File("D:\\user_info_low_cleaned.csv");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(csv), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String line = "";

        line = br.readLine();
        int i = 0;
        while((line=br.readLine()) !=null){
            User_check user = new User_check();
            i++;
            String[] u = line.split(",");
            user.setId2(u[0]);
            user.setUrl_token(u[1]);
            user.setName(u[2]);
            user.setGender(u[3]);
            user.setLevel(Integer.parseInt(u[4]));
            user.setAvatar_url(u[5]);
            user.setType(u[6]);
            user.setHeadline(u[7]);
            user.setDescription(u[8]);
            user.setBusiness(u[9]);
            user.setFollowing_count(Integer.parseInt(u[10]));
            user.setFollower_count(Integer.parseInt(u[11]));
            user.setAnswer_count(Integer.parseInt(u[12]));
            user.setZvideo_count(Integer.parseInt(u[13]));
            user.setQuestion_count(Integer.parseInt(u[14]));
            user.setArticles_count(Integer.parseInt(u[15]));
            user.setColumns_count(Integer.parseInt(u[16]));
            user.setFavorite_count(Integer.parseInt(u[17]));
            user.setFollowing_question_count(Integer.parseInt(u[18]));
            user.setFollowing_topic_count(Integer.parseInt(u[19]));
            user.setFollowing_columns_count(Integer.parseInt(u[20]));
            user.setFollowing_favlists_count(Integer.parseInt(u[21]));
            String temp = u[22].substring(1, u[22].length());
            user.setLocation(Arrays.asList(temp.split("，")));
            user.setVoteup_count(Integer.parseInt(u[23]));
if(i==24){
    break;
}
//            System.out.println(user.toString());
            list.add(user);
        }

        user_check_service.insert(list);


    }

    @Autowired
    Area_info_service area_info_service;

    public void insert_area_info() throws IOException {
        File csv = new File("D:\\quality_list.csv");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(csv), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String line = "";

        List<Area_info> list = new ArrayList<>();

        line = br.readLine();

        while((line=br.readLine()) !=null){
            Area_info area_info = new Area_info();
            String[] s = line.split(",");
            area_info.setId(6);
            area_info.setId2(s[1]);
            area_info.setValue(s[2]);
            area_info.setYear(getTime.getYear());
            area_info.setMonth(getTime.getMonth());
            list.add(area_info);
        }
        area_info_service.insert(list);
    }

}
