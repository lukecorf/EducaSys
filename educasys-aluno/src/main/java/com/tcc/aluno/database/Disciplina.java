package com.tcc.aluno.database;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TBL_DISCIPLINA")
public class Disciplina {


    @Id
    @Column( name = "ID_DISCIPLINA")
    @NotNull
    private long codigo;

    @NotNull
    @Column( name = "ST_NOME_DISCIPLINA")
    @Size(max = 100)
    private String nome;

    @NotNull
    @Column( name = "ST_NOME_PROFESSOR")
    @Size(max = 100)
    private String professor;

    @NotNull
    @Column( name = "URL_IMG")
    private String img;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "ID_PROFESSOR",nullable = false, referencedColumnName = "ID_PROFESSOR")
    private Professor professorfk;

    public Professor getProfessorfk() {
        return professorfk;
    }

    public void setProfessorfk(Professor professorfk) {
        this.professorfk = professorfk;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
