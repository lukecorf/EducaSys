package com.tcc.aluno.database;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TBL_ARQUIVOS")
public class Arquivo {

    @Id
    @Column( name = "ID_ARQUIVO")
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "URL_ARQUIVO")
    private String link;

    @NotNull
    @Column(name = "ST_NOME_ARQUIVO")
    @Size(max = 100)
    private String nome;

    @NotNull
    @Column(name = "NU_TAMANHO_ARQUIVO")
    private double tamanho;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "ID_DISCIPLINA",nullable = false, referencedColumnName = "ID_DISCIPLINA")
    private Disciplina disciplinafk;

    public Arquivo(@NotNull String link, @NotNull @Size(max = 100) String nome, @NotNull double tamanho, @NotNull Disciplina disciplinafk) {
        this.link = link;
        this.nome = nome;
        this.tamanho = tamanho;
        this.disciplinafk = disciplinafk;
    }

    public Arquivo(){
        
    }

    public Disciplina getDisciplinafk() {
        return disciplinafk;
    }

    public void setDisciplinafk(Disciplina disciplinafk) {
        this.disciplinafk = disciplinafk;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }
}
