package com.tcc.professor.database;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "TBL_ATIVIDADE")
public class Atividade {

    @Id
    @Column(name = "ID_ATIVIDADE")
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "NU_VALOR_ATIVIDADE")
    @DecimalMax("100.0")
    private Float valor;

    @NotNull
    @Column(name = "ST_NOME_ATIVIDADE")
    @Size(max = 100)
    private String nome;

    @NotNull
    @Column(name = "DT_DATA")
    private Date data;

    @NotNull
    @Column(name = "BO_TIPO_ATIVIDADE")
    private boolean tipo;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "ID_DISCIPLINA",nullable = false, referencedColumnName = "ID_DISCIPLINA")
    private Disciplina disciplinafk;

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

    @Override
    public String toString() {
        return "Atividade{" +
                "id=" + id +
                ", valor=" + valor +
                ", nome='" + nome + '\'' +
                ", data=" + data +
                ", tipo=" + tipo +
                ", disciplinafk=" + disciplinafk +
                '}';
    }
}
