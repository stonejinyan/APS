package com.aps.bean;

import lombok.Data;

import java.util.Set;
@Data
public class User {
    private String id;
    private String userName;
    private String password;
    private Set<Role> roles;

}
