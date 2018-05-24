package com.tcc.secretaria.DTO;


import java.time.LocalDate;

public class AlunoListDTO {
    private Long id_aluno;
    private String st_nome_aluno;
    private String co_email;
    private String co_telefone;
    private String dc_cpf;


    public AlunoListDTO(Long id_aluno, String st_nome_aluno, String co_email, String co_telefone, String dc_cpf) {
        this.id_aluno = id_aluno;
        this.st_nome_aluno = st_nome_aluno;
        this.co_email = co_email;
        this.co_telefone = co_telefone;
        this.dc_cpf = dc_cpf;

    }

    public AlunoListDTO(){

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

    public String getCo_email() {
        return co_email;
    }

    public void setCo_email(String co_email) {
        this.co_email = co_email;
    }

    public String getCo_telefone() {
        return co_telefone;
    }

    public void setCo_telefone(String co_telefone) {
        this.co_telefone = co_telefone;
    }

    public String getDc_cpf() {
        return dc_cpf;
    }

    public void setDc_cpf(String dc_cpf) {
        this.dc_cpf = dc_cpf;
    }
}
