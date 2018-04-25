package com.tcc.secretaria.DTO;

import java.util.Date;

public class ProfessorDTO {
    private Long id_professor;
    private String st_nome_professor ;
    private String co_telefone ;
    private String co_email ;
    private String dc_cpf ;
    private String dc_rg ;
    private String st_endereco ;
    private String pw_senha_prof;
    private Date dt_data_nasc;

    public ProfessorDTO(Long id_professor, String st_nome_professor, String co_telefone, String co_email, String dc_cpf, String dc_rg, String st_endereco, String pw_senha_prof, Date dt_data_nasc) {
        this.id_professor = id_professor;
        this.st_nome_professor = st_nome_professor;
        this.co_telefone = co_telefone;
        this.co_email = co_email;
        this.dc_cpf = dc_cpf;
        this.dc_rg = dc_rg;
        this.st_endereco = st_endereco;
        this.pw_senha_prof= pw_senha_prof;
        this.dt_data_nasc = dt_data_nasc;
    }

    public Long getId_professor() {
        return id_professor;
    }

    public void setId_professor(Long id_professor) {
        this.id_professor = id_professor;
    }

    public String getSt_nome_professor() {
        return st_nome_professor;
    }

    public void setSt_nome_professor(String st_nome_professor) {
        this.st_nome_professor = st_nome_professor;
    }

    public String getCo_telefone() {
        return co_telefone;
    }

    public void setCo_telefone(String co_telefone) {
        this.co_telefone = co_telefone;
    }

    public String getCo_email() {
        return co_email;
    }

    public void setCo_email(String co_email) {
        this.co_email = co_email;
    }

    public String getdc_cpf() {
        return dc_cpf;
    }

    public void setdc_cpf(String dc_cpf) {
        this.dc_cpf = dc_cpf;
    }

    public String getdc_rg() {
        return dc_rg;
    }

    public void setdc_rg(String dc_rg) {
        this.dc_rg = dc_rg;
    }

    public String getSt_endereco() {
        return st_endereco;
    }

    public void setSt_endereco(String st_endereco) {
        this.st_endereco = st_endereco;
    }

    public String getPw_senha_prof() {
        return pw_senha_prof;
    }

    public void setPw_senha_prof(String pw_senha_prof) {
        this.pw_senha_prof= pw_senha_prof;
    }

    public Date getDt_data_nasc() {
        return dt_data_nasc;
    }

    public void setDt_data_nasc(Date dt_data_nasc) {
        this.dt_data_nasc = dt_data_nasc;
    }
}
