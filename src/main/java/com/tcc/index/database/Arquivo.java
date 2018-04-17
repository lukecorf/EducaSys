package com.tcc.index.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TBL_ARQUIVOS")
public class Arquivo {

    @Id
    @Column( name = "ID_ARQUIVO")
    @NotNull
    long id;

    @NotNull
    @Column(name = "URL_ARQUIVO")
    String link;

    @NotNull
    @Column(name = "ST_NOME_ARQUIVO")
    @Size(max = 100)
    String nome;

    @NotNull
    @Column(name = "NU_TAMANHO_ARQUIVO")
    double tamanho;

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
