package com.tcc.secretaria.DTO;

public class ProfessorListDTO {
    private Long id_professor;
    private String st_nome_prof;
    private String dt_data_nasc;
    private String co_telefone;

    public ProfessorListDTO(Long id_professor, String st_nome_prof, String dt_data_nasc, String co_telefone) {
        this.id_professor = id_professor;
        this.st_nome_prof = st_nome_prof;
        this.dt_data_nasc = dt_data_nasc;
        this.co_telefone = co_telefone;
    }

    public Long getId_professor() {
        return id_professor;
    }

    public void setId_professor(Long id_professor) {
        this.id_professor = id_professor;
    }

    public String getSt_nome_prof() {
        return st_nome_prof;
    }

    public void setSt_nome_prof(String st_nome_prof) {
        this.st_nome_prof = st_nome_prof;
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
