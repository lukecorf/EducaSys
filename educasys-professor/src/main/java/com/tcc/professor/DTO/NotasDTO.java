package com.tcc.professor.DTO;

public class NotasDTO {
    private String nomeAluno;
    private Long idAtividade;
    private Long idAluno;
    private Float nota;

    public NotasDTO(String nomeAluno, Long idAtividade, Long idAluno, Float nota) {
        this.nomeAluno = nomeAluno;
        this.idAtividade = idAtividade;
        this.idAluno = idAluno;
        this.nota = nota;
    }

    public NotasDTO(){

    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Long getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(Long idAtividade) {
        this.idAtividade = idAtividade;
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Float getNota() {
        return nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "NotasDTO{" +
                "nomeAluno='" + nomeAluno + '\'' +
                ", idAtividade=" + idAtividade +
                ", idAluno=" + idAluno +
                ", nota=" + nota +
                '}';
    }
}
