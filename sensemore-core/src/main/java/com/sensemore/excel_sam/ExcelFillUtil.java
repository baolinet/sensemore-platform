package com.sensemore.excel_sam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillWrapper;

public class ExcelFillUtil {

    public static void readExcel(){
         String fileName = "/Users/bao.li001/Documents/用户模版.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(fileName, UserExcelData.class, new PageReadListener<UserExcelData>(dataList -> {
            for (UserExcelData demoData : dataList) {
                System.out.println(demoData.getName() + demoData.getAge() + demoData.getEmail());
            }
        })).sheet("Sheet1").doRead();
    }

    public static void templateFill() {

        List<UserExcelData> userDataList = new ArrayList<>();
        userDataList.add(new UserExcelData("John Doe", 30, "john.doe@example.com"));
        userDataList.add(new UserExcelData("Jane Smith", 25, "jane.smith@example.com"));
        UserExcelData userData = new UserExcelData("John Doe", 30, "john.doe@example.com");

        String templateFileName =  "/Users/bao.li001/Documents/用户模版.xlsx";
        String fileName =  "/Users/bao.li001/Documents/output" + System.currentTimeMillis() + ".xlsx";

        // EasyExcel.write(fileName,UserExcelData.class).withTemplate(templateFileName).sheet("Sheet2").doFill(userDataList);


        try (ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build()) {
            WriteSheet writeSheet2 = EasyExcel.writerSheet("Sheet2").build();
            WriteSheet writeSheet3 = EasyExcel.writerSheet("Sheet3").build();

            excelWriter.fill(userDataList, writeSheet2);
            excelWriter.fill(userData, writeSheet3);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("创建成功"+ fileName);
    }
}
