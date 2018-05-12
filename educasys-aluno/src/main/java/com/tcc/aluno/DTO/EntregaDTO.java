package com.tcc.aluno.DTO;

/**
 * Created by lukew on 11/05/2018.
 */
public class EntregaDTO {
    private Long id_aluno;
    private Long id_atividade;
    private String url;

    public EntregaDTO(Long id_aluno, Long id_atividade, String url) {
        this.id_aluno = id_aluno;
        this.id_atividade = id_atividade;
        this.url = url;
    }

    public EntregaDTO(){

    }

    public Long getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(Long id_aluno) {
        this.id_aluno = id_aluno;
    }

    public Long getId_atividade() {
        return id_atividade;
    }

    public void setId_atividade(Long id_atividade) {
        this.id_atividade = id_atividade;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
