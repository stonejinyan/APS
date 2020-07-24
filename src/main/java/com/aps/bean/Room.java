package com.aps.bean;

import lombok.Data;

@Data
public class Room {
    private String name;

    private Room() {
    }

    public Room(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
