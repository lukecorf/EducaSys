package com.tcc.index.DTO;


public class LoginDTO {

    private long   id_login;
    private int    nu_type;
    private String nu_code;

    public LoginDTO(long id_login, int nu_type, String nu_code) {
        this.id_login = id_login;
        this.nu_type = nu_type;
        this.nu_code = nu_code;
    }

    public LoginDTO(){

    }

    public long getId_login() {
        return id_login;
    }

    public void setId_login(long id_login) {
        this.id_login = id_login;
    }

    public int getNu_type() {
        return nu_type;
    }

    public void setNu_type(int nu_type) {
        this.nu_type = nu_type;
    }

    public String getNu_code() {
        return nu_code;
    }

    public void setNu_code(String nu_code) {
        this.nu_code = nu_code;
    }

}
