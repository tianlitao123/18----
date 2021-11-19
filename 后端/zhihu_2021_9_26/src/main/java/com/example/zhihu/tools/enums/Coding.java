package com.example.zhihu.tools.enums;



public enum Coding {

    // 成功
    SUCCESS(200),

    // 失败    页码查询申请失败 返回
    FAIL(400),

//    插入成功
    INSERT_SUCCESS(250),
//    插入失败
    INSERT_FAIL(450),



    // 未认证（签名错误）
    UNAUTHORIZED(401),

    // 接口不存在
    NOT_FOUND(404),

    // 服务器内部错误
    INTERNAL_SERVER_ERROR(500);

    public int code;

    Coding(int code) {
        this.code = code;
    }

}
