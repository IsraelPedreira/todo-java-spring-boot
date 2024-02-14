package com.israel.todolistjava.todolistjava.enums;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    PREMIUM_USER("premium_user");

    public String role;
    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
