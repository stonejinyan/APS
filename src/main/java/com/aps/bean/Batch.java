package com.aps.bean;

import lombok.Data;

@Data
public class Batch {
    private int batchID;
    private String batchName;
    private String batchCode;
    private int projectID;
}
