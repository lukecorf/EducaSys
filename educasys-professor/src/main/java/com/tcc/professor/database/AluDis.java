package com.tcc.professor.database;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_ALU_DIS")
public class AluDis {

    @Id
    @NotNull
    @Column(name = "ID_ALUDIS")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "ID_DISCIPLINA",nullable = false, referencedColumnName = "ID_DISCIPLINA")
    private Disciplina disciplinafk;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "ID_ALUNO",nullable = false, referencedColumnName = "ID_ALUNO")
    private Aluno alunofk;

    @NotNull
    @Column(name = "NU_FALTAS")
    private float faltas;

    public AluDis(@NotNull Disciplina disciplinafk, @NotNull Aluno alunofk, @NotNull float faltas) {
        this.disciplinafk = disciplinafk;
        this.alunofk = alunofk;
        this.faltas = faltas;
    }

    public AluDis(){

    }

    public float getFaltas() {
        return faltas;
    }

    public void setFaltas(float faltas) {
        this.faltas = faltas;
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
}
