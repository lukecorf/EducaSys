package com.tcc.index.TADs;

public class Usuario {
    String matricula;
    String nome;
    String periodoL;
    String dataN;
    boolean sexo;
    String email;
    String fone;
    String cpf;
    String RG;
    Integer tipo;

    public Usuario(String matricula, String nome, String periodoL, String dataN, boolean sexo, String email, String fone, String cpf, String RG, Integer tipo) {
        this.matricula = matricula;
        this.nome = nome;
        this.periodoL = periodoL;
        this.dataN = dataN;
        this.sexo = sexo;
        this.email = email;
        this.fone = fone;
        this.cpf = cpf;
        this.RG = RG;
        this.tipo = tipo;
    }

    public Usuario(String matricula, String nome, String periodoL) {
        this.matricula = matricula;
        this.nome = nome;
        this.periodoL = periodoL;
    }
}
