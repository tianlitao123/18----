package com.example.zhihu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @data 2021年10月16日9:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Areas {
    private int id;
    private String name;
    private String formula;

}
