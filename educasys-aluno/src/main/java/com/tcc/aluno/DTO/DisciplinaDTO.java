package com.tcc.aluno.DTO;

import java.util.ArrayList;

public class DisciplinaDTO {
    private Long id_disciplina;
    private String st_nome;
    private int nu_carga_horaria;
    private String tx_descricao;
    private String st_nome_prof;
    private String url_img;
    private Long id_professor;
    private ArrayList<AlunoListDTO> ls_alunos;

    public DisciplinaDTO(Long id_disciplina, String st_nome, int nu_carga_horaria, String tx_descricao, String st_nome_prof, String url_img, Long id_professor, ArrayList<AlunoListDTO> ls_alunos) {
        this.id_disciplina = id_disciplina;
        this.st_nome = st_nome;
        this.nu_carga_horaria = nu_carga_horaria;
        this.tx_descricao = tx_descricao;
        this.st_nome_prof = st_nome_prof;
        this.url_img = url_img;
        this.id_professor = id_professor;
        this.ls_alunos = ls_alunos;
    }

    public DisciplinaDTO(){

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

    public int getNu_carga_horaria() {
        return nu_carga_horaria;
    }

    public void setNu_carga_horaria(int nu_carga_horaria) {
        this.nu_carga_horaria = nu_carga_horaria;
    }

    public String getTx_descricao() {
        return tx_descricao;
    }

    public void setTx_descricao(String tx_descricao) {
        this.tx_descricao = tx_descricao;
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

    public Long getId_professor() {
        return id_professor;
    }

    public void setId_professor(Long id_professor) {
        this.id_professor = id_professor;
    }

    public ArrayList<AlunoListDTO> getLs_alunos() {
        return ls_alunos;
    }

    public void setLs_alunos(ArrayList<AlunoListDTO> ls_alunos) {
        this.ls_alunos = ls_alunos;
    }
}
