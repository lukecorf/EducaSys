package com.tcc.secretaria.DTO;

public class SecretariaDTO {
    long id_secretaria;
    String st_nome_secretaria;
    String st_code;
    String pw_senha_secretaria;

    public SecretariaDTO(long id_secretaria, String st_nome_secretaria, String st_code, String pw_senha_secretaria) {
        this.id_secretaria = id_secretaria;
        this.st_nome_secretaria = st_nome_secretaria;
        this.st_code = st_code;
        this.pw_senha_secretaria = pw_senha_secretaria;
    }

    public SecretariaDTO(){

    }

    public long getId_secretaria() {
        return id_secretaria;
    }

    public void setId_secretaria(long id_secretaria) {
        this.id_secretaria = id_secretaria;
    }

    public String getSt_nome_secretaria() {
        return st_nome_secretaria;
    }

    public void setSt_nome_secretaria(String st_nome_secretaria) {
        this.st_nome_secretaria = st_nome_secretaria;
    }

    public String getSt_code() {
        return st_code;
    }

    public void setSt_code(String st_code) {
        this.st_code = st_code;
    }

    public String getPw_senha_secretaria() {
        return pw_senha_secretaria;
    }

    public void setPw_senha_secretaria(String pw_senha_secretaria) {
        this.pw_senha_secretaria = pw_senha_secretaria;
    }
}
