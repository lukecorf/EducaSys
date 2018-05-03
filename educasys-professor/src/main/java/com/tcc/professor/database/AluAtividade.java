package com.tcc.professor.database;

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

    public AluAtividade(@NotNull Disciplina disciplinafk, @NotNull Aluno alunofk, @NotNull Atividade atividadefk, float nota) {
        this.disciplinafk = disciplinafk;
        this.alunofk = alunofk;
        this.atividadefk = atividadefk;
        this.nota = nota;
    }


    public AluAtividade(){

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
}
