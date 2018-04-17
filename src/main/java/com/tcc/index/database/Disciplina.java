package com.tcc.index.database;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TBL_DISCIPLINA")
public class Disciplina {


    @Id
    @Column( name = "ID_DISCIPLINA")
    @NotNull
    long codigo;


    @NotNull
    @Column( name = "ST_NOME_DISCIPLINA")
    @Size(max = 100)
    String nome;

    @NotNull
    @Column( name = "ST_NOME_PROFESSOR")
    @Size(max = 100)
    String professor;

    @NotNull
    @Column( name = "URL_IMG")
    String img;

    @ManyToOne
    Professor professorfk;

    @OneToMany
    Atividade atividadefk;

    public Atividade getAtividadefk() {
        return atividadefk;
    }

    public void setAtividadefk(Atividade atividadefk) {
        this.atividadefk = atividadefk;
    }

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
