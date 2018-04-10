package com.tcc.professor.TADs;

public class Disciplina {
    String codigo;
    String nome;
    String status;
    String professor;
    String proxProva;
    String img;
    float falta;
    float nota;

    public Disciplina(String codigo, String nome, String status, String professor, String proxProva, String img, float falta, float nota) {
        this.codigo = codigo;
        this.nome = nome;
        this.status = status;
        this.professor = professor;
        this.proxProva = proxProva;
        this.img = img;
        this.falta = falta;
        this.nota = nota;
    }

    public Disciplina(){

    }
}
