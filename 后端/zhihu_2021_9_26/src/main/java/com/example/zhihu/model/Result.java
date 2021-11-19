package com.example.zhihu.model;

import com.example.zhihu.tools.enums.Coding;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @data 2021年10月16日20:58
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Result<T> {

    private String message;
    private int code;
    private T data;

    public void setCode(Coding coding){
        this.code = coding.code;
    }

    

}
