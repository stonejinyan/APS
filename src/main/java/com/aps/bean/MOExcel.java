package com.aps.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class MOExcel extends BaseRowModel implements Serializable {
    @ExcelProperty(index = 0)
    private String panel;
    @ExcelProperty(index = 1)
    private String mo;
    @ExcelProperty(index = 2)
    private String des;
    @ExcelProperty(index = 3)
    private int quantity;
    @ExcelProperty(index = 4)
    private String customerID;
    @ExcelProperty(index = 5)
    private String cubiclesType;
    @ExcelProperty(index = 6)
    private String so;
    @ExcelProperty(index = 7)
    private String batch;
    @ExcelProperty(index = 8)
    private String wbs;
    @ExcelProperty(index = 9)
    private String projectName;
    @ExcelProperty(index = 10)
    private String classification;
    @ExcelProperty(index = 11)
    private String comments;
    @ExcelProperty(index = 12)
    private Date deliveryDate;
    @ExcelProperty(index = 13)
    private Date materialCompletenessDate;
    private int moID;
    private int modelID;
}
