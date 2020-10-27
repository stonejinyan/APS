package com.aps.bean;

import lombok.Data;

@Data
public class BatchTable {
    private int batchID;
    private String batchName;
    private String batchCode;

    private int projectID;
    private String projectName;
    private String projectCode;
}
