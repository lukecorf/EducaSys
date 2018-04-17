package com.tcc.index.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "TBL_ATIVIDADE")
public class Atividade {

    @Id
    @Column(name = "ID_ATIVIDADE")
    @NotNull
    long id;

    @NotNull
    @Column(name = "NU_VALOR_ATIVIDADE")
    @Size(max = 100)
    float valor;

    @NotNull
    @Column(name = "ST_NOME_ATIVIDADE")
    @Size(max = 100)
    String nome;

    @NotNull
    @Column(name = "DT_DATA")
    Date data;

    @NotNull
    @Column(name = "BO_TIPO_ATIVIDADE")
    boolean tipo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }
}
