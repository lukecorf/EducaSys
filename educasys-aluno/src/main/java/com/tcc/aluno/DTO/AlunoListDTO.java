package com.tcc.aluno.DTO;


public class AlunoListDTO {
    private Long id_aluno;
    private String st_nome_aluno;
    private String co_email;
    private String co_telefone;

    public AlunoListDTO(Long id_aluno, String st_nome_aluno, String co_email, String co_telefone) {
        this.id_aluno = id_aluno;
        this.st_nome_aluno = st_nome_aluno;
        this.co_email = co_email;
        this.co_telefone = co_telefone;
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

    public String getco_email() {
        return co_email;
    }

    public void setDt_data_nasc(String co_email) {
        this.co_email= co_email;
    }

    public String getCo_telefone() {
        return co_telefone;
    }

    public void setCo_telefone(String co_telefone) {
        this.co_telefone = co_telefone;
    }
}
