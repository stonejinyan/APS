package com.aps.bean;

public class Permissions {
    private String id;
    private String permissionsName;

    public String getId() {
        return id;
    }

    public String getPermissionsName() {
        return permissionsName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPermissionsName(String permissionsName) {
        this.permissionsName = permissionsName;
    }

    public Permissions() {
    }

    public Permissions(String id, String permissionsName) {
        this.id = id;
        this.permissionsName = permissionsName;
    }
}
