package com.tcc.professor.DTO;

/**
 * Created by lukew on 11/05/2018.
 */
public class AluAtividadeDTO {

    private String nome;
    private String url;

    public AluAtividadeDTO(String nome, String url) {
        this.nome = nome;
        this.url = url;
    }

    public AluAtividadeDTO(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
