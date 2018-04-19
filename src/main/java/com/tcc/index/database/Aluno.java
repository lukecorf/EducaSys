package com.tcc.index.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "TBL_ALUNO")
public class Aluno {

    @Id
    @Column(name = "ID_ALUNO")
    @NotNull
    private long id;

    @NotNull
    @Column( name = "ST_NOME_ALUNO")
    @Size(max=100)
    private String nome;

    @NotNull
    @Column( name = "ST_NOME_PAI")
    @Size(max=100)
    private String nomeP;

    @NotNull
    @Column( name = "ST_NOME_MAE")
    @Size(max=100)
    private String nomeM;

    @NotNull
    @Column( name = "ST_ENDERECO")
    @Size(max=300)
    private String endereco;

    @NotNull
    @Column( name = "CO_EMAIL")
    @Size(max=100)
    private String email;

    @NotNull
    @Column( name = "CO_TELEFONE")
    @Size(max=11)
    private String telefone;

    @NotNull
    @Column( name = "DT_DATA_NASC")
    @Size(max=100)
    private Date dataN;

    @Column( name = "DC_CPF")
    @Size(max=11)
    private String cpf;

    @Column( name = "DC_RG")
    @Size(max=11)
    private String rg;

    @Column( name = "URL_IMG_ALUNO")
    private String url;

    @NotNull
    @Column( name = "PW_SENHA_ALUNO")
    @Size(max=50)
    private String senha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataN() {
        return dataN;
    }

    public void setDataN(Date dataN) {
        this.dataN = dataN;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
