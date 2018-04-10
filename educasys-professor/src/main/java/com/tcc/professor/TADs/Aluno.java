package com.tcc.professor.TADs;

public class Aluno {
    String matricula;
    String nome;
    String periodoL;
    String dataN;
    boolean sexo;
    String email;
    String fone;
    String cpf;
    String RG;

    public Aluno(String matricula, String nome, String periodoL, String dataN, boolean sexo, String email, String fone, String cpf, String RG) {
        this.matricula = matricula;
        this.nome = nome;
        this.periodoL = periodoL;
        this.dataN = dataN;
        this.sexo = sexo;
        this.email = email;
        this.fone = fone;
        this.cpf = cpf;
        this.RG = RG;
    }

    public Aluno(String matricula, String nome, String periodoL) {
        this.matricula = matricula;
        this.nome = nome;
        this.periodoL = periodoL;
    }
}
