package com.tcc.index.TADs;

/**
 * Created by lukew on 14/02/2018.
 */
public class LoginObject {
    private String id;
    private String password;

    public LoginObject(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public LoginObject(){

    }

    public String getCode() {
        return id;
    }

    public void setCode(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}


