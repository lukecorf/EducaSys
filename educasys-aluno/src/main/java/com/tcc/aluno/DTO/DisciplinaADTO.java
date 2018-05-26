package com.tcc.aluno.DTO;

import java.util.ArrayList;
import java.util.Date;

public class DisciplinaADTO {
    private Long id_disciplina;
    private String st_nome;
    private Long id_professor;
    private String st_nome_prof;
    private String url_img;
    private int nu_faltas;
    private String dt_next_prova;
    private Double nu_nota;
    private Long id_aluno;
    private int nu_carga_horaria;

    public DisciplinaADTO(Long id_disciplina, String st_nome, Long id_professor, String st_nome_prof, String url_img, int nu_faltas, String dt_next_prova, Double nu_nota, Long id_aluno, int nu_carga_horaria) {
        this.id_disciplina = id_disciplina;
        this.st_nome = st_nome;
        this.id_professor = id_professor;
        this.st_nome_prof = st_nome_prof;
        this.url_img = url_img;
        this.nu_faltas = nu_faltas;
        this.dt_next_prova = dt_next_prova;
        this.nu_nota = nu_nota;
        this.id_aluno = id_aluno;
        this.nu_carga_horaria = nu_carga_horaria;
    }

    public DisciplinaADTO(){

    }

    public int getNu_carga_horaria() {
        return nu_carga_horaria;
    }

    public void setNu_carga_horaria(int nu_carga_horaria) {
        this.nu_carga_horaria = nu_carga_horaria;
    }

    public Long getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(Long id_disciplina) {
        this.id_disciplina = id_disciplina;
    }

    public String getSt_nome() {
        return st_nome;
    }

    public void setSt_nome(String st_nome) {
        this.st_nome = st_nome;
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

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public int getNu_faltas() {
        return nu_faltas;
    }

    public void setNu_faltas(int nu_faltas) {
        this.nu_faltas = nu_faltas;
    }

    public String getDt_next_prova() {
        return dt_next_prova;
    }

    public void setDt_next_prova(String dt_next_prova) {
        this.dt_next_prova = dt_next_prova;
    }

    public Double getNu_nota() {
        return nu_nota;
    }

    public void setNu_nota(Double nu_nota) {
        this.nu_nota = nu_nota;
    }

    public Long getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(Long id_aluno) {
        this.id_aluno = id_aluno;
    }
}
