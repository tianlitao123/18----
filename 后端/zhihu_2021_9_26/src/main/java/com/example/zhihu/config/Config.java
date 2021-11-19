package com.example.zhihu.config;


import com.example.zhihu.mapper.All_figure_mapper;
import com.example.zhihu.model.*;
import com.example.zhihu.model.showData.Bangdan;
import com.example.zhihu.model.showData.Data_table;
import com.example.zhihu.model.showData.Super_index_table;
import com.example.zhihu.model.showData.Super_table;
import com.example.zhihu.model.temp_class.NameAndFrequency;
import com.example.zhihu.model.temp_class.NameAndValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @data 2021年10月12日13:34
 */

@Configuration

public class Config {
    @Bean
    public User User(){
        return new User();
    }

    @Bean
    public Areas areas(){
        return new Areas();
    }

    @Bean
    public Area_info area_info(){
        return new Area_info();
    }

    @Bean
    public Result result(){
        return new Result();
    }

    @Bean
    public Download download(){
        return new Download();
    }


    @Bean
    public NameAndValue nameAndValue(){
        return new NameAndValue();
    }

    @Bean
    public Location_data location_data(){
        return new Location_data();
    }

    @Bean
    public Super_table super_table(){
        return new Super_table();
    }

    @Bean
    public Super_index_table super_index_table(){
        return new Super_index_table();
    }

    @Bean
    public Data_table data_table(){
        return new Data_table();
    }

    @Bean
    public User_check user_check(){
        return new User_check();
    }

    @Bean
    public User_date user_date(){
        return new User_date();
    }

    @Bean
    public NameAndFrequency nameAndFrequency() {
        return new NameAndFrequency();
    }


    @Bean
    public All_figure all_figure(){
        return new All_figure();
    }

    @Bean
    public User_draw user_draw(){
        return new User_draw();
    }

    @Bean
    public Bangdan bangdan(){
        return new Bangdan();
    }
}
