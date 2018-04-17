package com.tcc.index.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_ALU_DIS")
public class AluDis {

    @ManyToOne
    Disciplina disciplinafk;

    @ManyToOne
    Aluno alunofk;

    @NotNull
    @Column(name = "NU_FALTAS")
    float faltas;

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

    public float getFaltas() {
        return faltas;
    }

    public void setFaltas(float faltas) {
        this.faltas = faltas;
    }
}
