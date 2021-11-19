package com.example.zhihu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @data 2021年10月16日9:06
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Area_info implements Serializable {
    private Integer id;
    private String id2;
    private String value;
    private Integer year;
    private Integer month;
}
