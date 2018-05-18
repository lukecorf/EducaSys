package com.tcc.aluno.database;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "TBL_ALU_ATIVIDADE")
public class AluAtividade {
    @Id
    @NotNull
    @Column(name = "ID_ALU_ATIVIDADE")
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name ="ID_DISCIPLINA",nullable = false, referencedColumnName = "ID_DISCIPLINA")
    private Disciplina disciplinafk;

    @ManyToOne
    @NotNull
    @JoinColumn(name ="ID_ALUNO",nullable = false, referencedColumnName = "ID_ALUNO")
    private Aluno alunofk;

    @ManyToOne
    @NotNull
    @JoinColumn(name ="ID_ATIVIDADE",nullable = false, referencedColumnName = "ID_ATIVIDADE")
    private Atividade atividadefk;

    @Column(name = "NU_NOTA")
    private float nota;

    @Column(name = "BO_ENTREGA")
    private boolean entrega;

    @Column(name = "URL_ENTREGA")
    private String url;

    public AluAtividade(@NotNull Disciplina disciplinafk, @NotNull Aluno alunofk, @NotNull Atividade atividadefk, float nota, boolean entrega, String url) {
        this.disciplinafk = disciplinafk;
        this.alunofk = alunofk;
        this.atividadefk = atividadefk;
        this.nota = nota;
        this.entrega = entrega;
        this.url = url;
    }

    public AluAtividade(){

    }

    public boolean isEntrega() {
        return entrega;
    }

    public void setEntrega(boolean entrega) {
        this.entrega = entrega;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Disciplina getDisciplinafk() {
        return disciplinafk;
    }

    public void setDisciplinafk(Disciplina disciplinafk) {
        this.disciplinafk = disciplinafk;
    }

    public Aluno getAlunofk() {
        return alunofk;
    }

    public void setAlunofk(Aluno alunofk) {
        this.alunofk = alunofk;
    }

    public Atividade getAtividadefk() {
        return atividadefk;
    }

    public void setAtividadefk(Atividade atividadefk) {
        this.atividadefk = atividadefk;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "AluAtividade{" +
                "id=" + id +
                ", disciplinafk=" + disciplinafk.getCodigo() +
                ", alunofk=" + alunofk.getId() +
                ", atividadefk=" + atividadefk.getId() +
                ", nota=" + nota +
                ", entrega=" + entrega +
                ", url='" + url + '\'' +
                '}';
    }
}
