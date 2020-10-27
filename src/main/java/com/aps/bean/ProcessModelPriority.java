package com.aps.bean;

import lombok.Data;

@Data
public class ProcessModelPriority {
    private int processID;
    private int productModelID;
    private int priority;

    public ProcessModelPriority() {
    }

    public ProcessModelPriority(int processID, int productModelID, int priority) {
        this.processID = processID;
        this.productModelID = productModelID;
        this.priority = priority;
    }
}
