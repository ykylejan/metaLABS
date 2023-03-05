package com.metaLabs;

public class LoginEncaps {
    private String username;
    private String password;
    
    public void Set_Username(String username) {
        this.username = username;
    }
    public void Set_Passwprd(String password) {
        this.password = password;
    }
    
    public String Get_Username() {
        return this.username;
    }
    public String Get_Password() {
        return this.password;
    }
    
}
