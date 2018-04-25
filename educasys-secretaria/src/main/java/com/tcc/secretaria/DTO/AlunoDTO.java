package com.tcc.secretaria.DTO;

import java.util.Date;

public class AlunoDTO {
    private Long id_aluno;
    private String st_nome_aluno ;
    private String co_telefone ;
    private String co_email ;
    private String st_nome_pai ;
    private String st_nome_mae ;
    private String st_endereco ;
    private String pw_senha_aluno ;
    private Date dt_data_nasc;

    public AlunoDTO(Long id_aluno, String st_nome_aluno, String co_telefone, String co_email, String st_nome_pai, String st_nome_mae, String st_endereco, String pw_senha_aluno, Date dt_data_nasc) {
        this.id_aluno = id_aluno;
        this.st_nome_aluno = st_nome_aluno;
        this.co_telefone = co_telefone;
        this.co_email = co_email;
        this.st_nome_pai = st_nome_pai;
        this.st_nome_mae = st_nome_mae;
        this.st_endereco = st_endereco;
        this.pw_senha_aluno = pw_senha_aluno;
        this.dt_data_nasc = dt_data_nasc;
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

    public Date getDt_data_nasc() {
        return dt_data_nasc;
    }

    public void setDt_data_nasc(Date dt_data_nasc) {
        this.dt_data_nasc = dt_data_nasc;
    }
}
