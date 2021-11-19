//package com.example.zhihu.demo;
//
//import com.example.zhihu.mapper.All_figure_mapper;
//import com.example.zhihu.mapper.Area_info_mapper;
//import com.example.zhihu.model.All_figure;
//import com.example.zhihu.model.Result;
//import com.example.zhihu.model.User;
//
//
//import com.example.zhihu.model.User_draw;
//import com.example.zhihu.service.All_figure_service;
//import com.example.zhihu.service.Area_info_service;
//import com.example.zhihu.service.Location_service;
//
//import com.example.zhihu.service.User_service;
//import com.example.zhihu.tools.CSVReader;
//import com.example.zhihu.tools.Insert_locations;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.*;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//
//@SpringBootTest
//class DemoApplicationTests {
//    @Autowired
//    private User user;
//
//    @Autowired
//    private Result result;
//
//    @Autowired
//    private User_service user_service;
//
//    @Autowired
//    Location_service location_service;
//
//    @Autowired
//    Insert_locations insert_locations;
//
//    @Autowired
//    CSVReader csvReader;
//
//    @Test
//    void contextLoads() {
////        List users = new ArrayList();
////        user.setId2("fjahkj123");
////        user.setGender("喀什的饭卡上的控件和");
//////        List location = new ArrayList();
//////        location.add("北京");
//////        location.add("上海");
//////        user.setLocation_name(location);
////        users.add(user);
////
////
////        System.out.println(this.user_service.insert(users));
////        location_service.insert("asdf");
//
//
//
//    }
//    @Test
//    public void test1(){
////        List<String> location_list=new ArrayList<>();
////        location_list.add("456");
////        location_list.add("789");
////        User user;
//////        System.out.println(user);
////        List<User> user_list=new ArrayList<>();
////        for (int i = 0; i < 1000; i++) {
////            user=new User(1,"1","1-2","name","nan",5,"444","ff","45","jlk","afs",56.56,56.56,56.56,56.56,56.56,56.56,56.56,56.56,56.456,5656.5,5.5,5.5,location_list,56.56);
////            user_list.add(user);
////        }
////        System.out.println(user_list.size());
////        Result insert = user_service.insert(user_list);
////        System.out.println(insert);
//    }
//
//
//
//    @Test
//    public void test9(){
////        Map<Integer,ArrayList<Map<String,Integer>>> map=new HashMap<>();
////        ArrayList<Map<String, Integer>> list=new ArrayList<>();
////        Map<String,Integer> userMap=new HashMap<>();
////        userMap.put("123",456);
////        list.add(userMap);
////        list.add(userMap);
////        list.add(userMap);
////        map.put(1,list);
////        System.out.println(map);
//
//    }
//
//    @Test
//    public void t(){
////        while(true){
////
////
////            try {
////                Thread.sleep(1000);
////                System.out.println("asdf");
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////
////        }
//    }
//
//
//
//
//
//    private String path="D:\\t.txt";
//@Test
//
//    public void read_file(){
//
////    System.out.println(path);
////        File file = new File(path);
////        while (true){
////            if(file.exists()){
////                try {
////                    Thread.sleep(3000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
////            }else{
//////                file.getParentFile().mkdir();
////                try {
////                    file.createNewFile();
////                    FileWriter fileWriter = new FileWriter(file);
////                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
////                    bufferedWriter.write("id2");
////                    bufferedWriter.flush();
////                    bufferedWriter.close();
////                    fileWriter.close();
////
////
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////                return ;
////            }
////        }
//    }
//
//    @Autowired
//    private All_figure_mapper all_figure_mapper;
//
//    @Autowired
//    private User_draw user_draw;
//
//    @Autowired
//    private All_figure all_figure;
//
//    @Test
//    public void User_draw(){
////        System.out.println(all_figure_mapper.get_idByTableName("sex"));
//
////        user_draw.setId(1);
////        user_draw.setKey("sex");
////        user_draw.setKey_value("女");
////        user_draw.setNum(123);
////        user_draw.setYear(2001);
////        user_draw.setMonth(1);
////        all_figure_mapper.insert_userDraw(user_draw);
//
////        all_figure.setId(2);
////        all_figure.setKey1("sex");
////        all_figure.setKey2("level");
////        List<All_figure> list = all_figure_mapper.get_userDraw2(all_figure);
////        System.out.println(list);
//
////        user_draw.setId(2);
////        user_draw.setKey("sex");
////        user_draw.setKey_value("男");
////        user_draw.setNum(35);
////        user_draw.setYear(2001);
////        user_draw.setMonth(1);
////        System.out.println(all_figure_mapper.is_saved(user_draw));
//    }
//
//    @Test
//    public void singal() throws FileNotFoundException, UnsupportedEncodingException {
//        csvReader.readCSV2();
//
//    }
//
//@Test
//    public void test_integer() throws FileNotFoundException, UnsupportedEncodingException {
////        csvReader.readCSV();
//        File file = new File("D:\\binary");
//        String[] list = file.list();
////        String[] file_name_list = new String[156];
//        List<String> file_list = new ArrayList<String>();
//
//        for(String temp: list){
//            file_list.add(temp.substring(0, temp.indexOf(".")));
//        }
//        for(int i = 0 ; i < file_list.size(); i++){
//            String[] temp = file_list.get(i).split("-");
//            String newName = temp[1] + "-" + temp[0];
//            for(int j = i+ 1; j < file_list.size(); j++){
//                if(newName.equals(file_list.get(j))){
//                    file_list.remove(j);
//                }
//            }
//        }
//
//
//        for(String temp : file_list){
//            System.out.println(temp);
//        }
//        csvReader.readCSV(file_list);
////        for(int i =0; i < list.length; i++){
////            file_name_list[i] = list[i].substring(0, list[i].indexOf("."));
////        }
////
////
////        for(int i = 0; i < file_name_list.length; i++){
//////            文件名根据-分割
////            String[] temp = file_name_list[i].split("-");
//////            名字反转
////            String newName = temp[1] + "-" +  temp[0];
////            for (int j=i+1; j < file_name_list.length; j++){
////                if(newName.equals(file_name_list[j])){
////                    file_name_list[j]=null;
////                }
////            }
////        }
////
////        for(String item: file_name_list){
////            System.out.println(item);
////        }
////        Date date = new Date();
////        SimpleDateFormat year = new SimpleDateFormat("yyyy");
////        SimpleDateFormat month = new SimpleDateFormat("MM");
////
////    System.out.println(year.format(date)  +  " "  + month.format(date));
//    }
//
//
////    @Test
////    public void saveUserDrawFromCsv() throws FileNotFoundException, UnsupportedEncodingException {
////        List<String>  new_file_list = new ArrayList<>();
////
////        try {
////            File file = new File("D:\\新建文本文档.txt");
////            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
////            BufferedReader bf = new BufferedReader(inputReader);
////            String temp = "";
////            while((temp = bf.readLine()) != null) {
////                    new_file_list.add(temp);
////
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//////        System.out.println(new_file_list.toString());
////        csvReader.readCSV(new_file_list);
////    }
//
//@Autowired
//    All_figure_service all_figure_service;
////    @Test
////    public void ttt(){
////        List<All_figure> list = new ArrayList<>();
////
////        all_figure.setKey1("0");
////        all_figure.setKey_value1("0");
////        all_figure.setKey2("1");
////        all_figure.setKey_value2("1");
////        all_figure.setNum(0);
////        all_figure.setYear(0);
////        all_figure.setMonth(0);
////        list.add(all_figure);
////        all_figure.setKey1("2");
////        all_figure.setKey_value1("2");
////        all_figure.setKey2("3");
////        all_figure.setKey_value2("3");
////        all_figure.setNum(0);
////        all_figure.setYear(0);
////        all_figure.setMonth(0);
////        list.add(all_figure);
////        System.out.println(list.toString());
////        all_figure_service.insert_userDaraw1_many(list);
////    }
//
//
//
//    @Test
//    public void tttt(){
//        int t = 0;
//        List<Integer> list = new ArrayList<>();
//        for(int i = 0; i < 10; i++){
//            t++;
//            list.add(t);
//        }
//        System.out.println(list.toString());
//    }
//
//    @Test
//    public void read_user() throws IOException {
//        csvReader.insert_user_check();
//    }
//
//    @Autowired
//    Area_info_service area_info_service;
//
//    @Test
//    public void bangdan() throws IOException {
//        csvReader.insert_area_info();
//    }
//}
