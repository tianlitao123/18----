package com.example.zhihu.tools;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @data 2021年10月26日16:43
 */

@Component
@NoArgsConstructor
public class Transfer_id2 {

    @Value("${path1}")
    private String path;

    public void read_file(String id2){
//        System.out.println(path);
        File file = new File(path);
        while (true){
            if(file.exists()){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{

                try {

                    file.createNewFile();
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(id2);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    fileWriter.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }
                return ;
            }
        }
    }
}
