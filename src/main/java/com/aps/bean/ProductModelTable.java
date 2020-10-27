package com.aps.bean;

import lombok.Data;

@Data
public class ProductModelTable {
    private int productModelID;
    private int productClassifyID;
    private String productModelName;
    private String productClassifyName;
    private Double tt;
    private Double lt;
    private Double ltIncludeFat;
    private Double ltToFat;
}
