package com.tcc.secretaria.DTO;

import java.time.LocalDate;


public class AlunoDTO {
    private Long id_aluno;
    private String st_nome_aluno ;
    private String co_telefone ;
    private String co_email ;
    private String st_nome_pai ;
    private String st_nome_mae ;
    private String st_endereco ;
    private String pw_senha_aluno ;
    private String dc_cpf;
    private String dc_rg;
    private String dt_data_nasc;
    private String url_img_aluno;

    public AlunoDTO(Long id_aluno, String st_nome_aluno, String co_telefone, String co_email, String st_nome_pai, String st_nome_mae, String st_endereco, String pw_senha_aluno, String dc_cpf, String dc_rg, String dt_data_nasc, String url_img_aluno) {
        this.id_aluno = id_aluno;
        this.st_nome_aluno = st_nome_aluno;
        this.co_telefone = co_telefone;
        this.co_email = co_email;
        this.st_nome_pai = st_nome_pai;
        this.st_nome_mae = st_nome_mae;
        this.st_endereco = st_endereco;
        this.pw_senha_aluno = pw_senha_aluno;
        this.dc_cpf = dc_cpf;
        this.dc_rg = dc_rg;
        this.dt_data_nasc = dt_data_nasc;
        this.url_img_aluno = url_img_aluno;
    }

    public AlunoDTO(){

    }

    public String getDc_cpf() {
        return dc_cpf;
    }

    public void setDc_cpf(String dc_cpf) {
        this.dc_cpf = dc_cpf;
    }

    public String getDc_rg() {
        return dc_rg;
    }

    public void setDc_rg(String dc_rg) {
        this.dc_rg = dc_rg;
    }

    public String getUrl_img_aluno() {
        return url_img_aluno;
    }

    public void setUrl_img_aluno(String url_img_aluno) {
        this.url_img_aluno = url_img_aluno;
    }

    public Long getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(Long id_aluno) {
        this.id_aluno = id_aluno;
    }

    public String getSt_nome_aluno() {
        return st_nome_aluno;
    }

    public void setSt_nome_aluno(String st_nome_aluno) {
        this.st_nome_aluno = st_nome_aluno;
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

    public String getSt_nome_pai() {
        return st_nome_pai;
    }

    public void setSt_nome_pai(String st_nome_pai) {
        this.st_nome_pai = st_nome_pai;
    }

    public String getSt_nome_mae() {
        return st_nome_mae;
    }

    public void setSt_nome_mae(String st_nome_mae) {
        this.st_nome_mae = st_nome_mae;
    }

    public String getSt_endereco() {
        return st_endereco;
    }

    public void setSt_endereco(String st_endereco) {
        this.st_endereco = st_endereco;
    }

    public String getPw_senha_aluno() {
        return pw_senha_aluno;
    }

    public void setPw_senha_aluno(String pw_senha_aluno) {
        this.pw_senha_aluno = pw_senha_aluno;
    }

    public String getDt_data_nasc() {
        return dt_data_nasc;
    }

    public void setDt_data_nasc(String dt_data_nasc) {
        this.dt_data_nasc = dt_data_nasc;
    }

    @Override
    public String toString() {
        return "AlunoDTO{" +
                "id_aluno=" + id_aluno +
                ", st_nome_aluno='" + st_nome_aluno + '\'' +
                ", co_telefone='" + co_telefone + '\'' +
                ", co_email='" + co_email + '\'' +
                ", st_nome_pai='" + st_nome_pai + '\'' +
                ", st_nome_mae='" + st_nome_mae + '\'' +
                ", st_endereco='" + st_endereco + '\'' +
                ", pw_senha_aluno='" + pw_senha_aluno + '\'' +
                ", dc_cpf='" + dc_cpf + '\'' +
                ", dc_rg='" + dc_rg + '\'' +
                ", dt_data_nasc=" + dt_data_nasc +
                ", url_img_aluno='" + url_img_aluno + '\'' +
                '}';
    }
}
