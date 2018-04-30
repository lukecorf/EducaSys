package com.tcc.secretaria.database;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TBL_DISCIPLINA")
public class Disciplina {


    @Id
    @Column( name = "ID_DISCIPLINA")
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codigo;

    @NotNull
    @Column( name = "ST_NOME")
    @Size(max = 100)
    private String nome;

    @NotNull
    @Column( name = "ST_NOME_PROF")
    @Size(max = 100)
    private String professor;

    @NotNull
    @Column( name = "NU_CARGA_HORARIA")
    private int cargaH;

    @Column( name = "TX_DESCRICAO")
    private String descricao;

    @NotNull
    @Column( name = "URL_IMG")
    private String img;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "ID_PROFESSOR",nullable = false, referencedColumnName = "ID_PROFESSOR")
    private Professor professorfk;

    public Disciplina(){

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

    public int getCargaH() {
        return cargaH;
    }

    public void setCargaH(int cargaH) {
        this.cargaH = cargaH;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Professor getProfessorfk() {
        return professorfk;
    }

    public void setProfessorfk(Professor professorfk) {
        this.professorfk = professorfk;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", professor='" + professor + '\'' +
                ", cargaH=" + cargaH +
                ", descricao='" + descricao + '\'' +
                ", img='" + img + '\'' +
                ", professorfk=" + professorfk +
                '}';
    }
}
