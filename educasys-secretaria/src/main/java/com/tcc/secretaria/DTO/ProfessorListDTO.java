package com.tcc.secretaria.DTO;


public class ProfessorListDTO {
    private Long id_professor;
    private String st_nome_professor;
    private String co_email;
    private String co_telefone;
    private String dc_cpf;

    public ProfessorListDTO(Long id_professor, String st_nome_prof, String co_email, String co_telefone, String dc_cpf) {
        this.id_professor = id_professor;
        this.st_nome_professor = st_nome_prof;
        this.co_email = co_email;
        this.co_telefone = co_telefone;
        this.dc_cpf = dc_cpf;
    }

    public String getCo_email() {
        return co_email;
    }

    public void setCo_email(String co_email) {
        this.co_email = co_email;
    }

    public Long getId_professor() {
        return id_professor;
    }

    public void setId_professor(Long id_professor) {
        this.id_professor = id_professor;
    }

    public String getSt_nome_prof() {
        return st_nome_professor;
    }

    public void setSt_nome_prof(String st_nome_prof) {
        this.st_nome_professor = st_nome_prof;
    }

    public String getCo_telefone() {
        return co_telefone;
    }

    public void setCo_telefone(String co_telefone) {
        this.co_telefone = co_telefone;
    }

    public String getSt_nome_professor() {
        return st_nome_professor;
    }

    public void setSt_nome_professor(String st_nome_professor) {
        this.st_nome_professor = st_nome_professor;
    }

    public String getDc_cpf() {
        return dc_cpf;
    }

    public void setDc_cpf(String dc_cpf) {
        this.dc_cpf = dc_cpf;
    }
}
