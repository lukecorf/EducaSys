package com.tcc.secretaria.DTO;


public class AlunoListDTO {
    private Long id_aluno;
    private String st_nome_aluno;
    private String dt_data_nasc;
    private String co_telefone;

    public AlunoListDTO(Long id_aluno, String st_nome_aluno, String dt_data_nasc, String co_telefone) {
        this.id_aluno = id_aluno;
        this.st_nome_aluno = st_nome_aluno;
        this.dt_data_nasc = dt_data_nasc;
        this.co_telefone = co_telefone;
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

    public String getDt_data_nasc() {
        return dt_data_nasc;
    }

    public void setDt_data_nasc(String dt_data_nasc) {
        this.dt_data_nasc = dt_data_nasc;
    }

    public String getCo_telefone() {
        return co_telefone;
    }

    public void setCo_telefone(String co_telefone) {
        this.co_telefone = co_telefone;
    }
}
