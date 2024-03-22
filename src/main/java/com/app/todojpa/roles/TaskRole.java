package com.app.todojpa.roles;

public enum TaskRole {
    ALTA("alta"),
    MEDIA("media"),
    BAIXA("baixa");
    
    private String role;

    TaskRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
