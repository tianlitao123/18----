package com.example.zhihu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * @data 2021年10月19日8:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Download {
    private BigInteger id;
    private BigInteger id1;
    private String name;
    private String url;
}
