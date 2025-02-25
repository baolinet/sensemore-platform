package com.sensemore.excel_sam;

import lombok.Data;

@Data
public class UserExcelData {
    private String name;
    private Integer age;
    private String email;

    public UserExcelData() {

    }

    // 构造函数
    public UserExcelData(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
