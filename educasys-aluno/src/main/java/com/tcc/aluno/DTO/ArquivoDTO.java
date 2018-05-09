package com.tcc.aluno.DTO;

/**
 * Created by lukew on 03/05/2018.
 */
public class ArquivoDTO {

    private long id_arquivo;
    private String url_arquivo;
    private String st_nome_arquivo;
    private double nu_tamanho_arquivo;
    private long id_disciplina;

    public ArquivoDTO(long id_arquivo, String url_arquivo, String st_nome_arquivo, double nu_tamanho_arquivo, long id_disciplina) {
        this.id_arquivo = id_arquivo;
        this.url_arquivo = url_arquivo;
        this.st_nome_arquivo = st_nome_arquivo;
        this.nu_tamanho_arquivo = nu_tamanho_arquivo;
        this.id_disciplina = id_disciplina;
    }

    public ArquivoDTO(){

    }

    public long getId_arquivo() {
        return id_arquivo;
    }

    public void setId_arquivo(long id_arquivo) {
        this.id_arquivo = id_arquivo;
    }

    public String getUrl_arquivo() {
        return url_arquivo;
    }

    public void setUrl_arquivo(String url_arquivo) {
        this.url_arquivo = url_arquivo;
    }

    public String getSt_nome_arquivo() {
        return st_nome_arquivo;
    }

    public void setSt_nome_arquivo(String st_nome_arquivo) {
        this.st_nome_arquivo = st_nome_arquivo;
    }

    public double getNu_tamanho_arquivo() {
        return nu_tamanho_arquivo;
    }

    public void setNu_tamanho_arquivo(double nu_tamanho_arquivo) {
        this.nu_tamanho_arquivo = nu_tamanho_arquivo;
    }

    public long getId_disciplina() {
        return id_disciplina;
    }

    public void setId_disciplina(long id_disciplina) {
        this.id_disciplina = id_disciplina;
    }
}
