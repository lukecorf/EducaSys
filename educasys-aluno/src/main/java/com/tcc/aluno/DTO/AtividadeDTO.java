package com.tcc.aluno.DTO;

import java.sql.Date;

public class AtividadeDTO {
    private long id_atividade;
    private long id_diciplina;
    private float nu_valor_atividade;
    private String st_nome_atividade;
    private Date dt_data;
    private boolean bo_tipo_atividade;
    private boolean entrega;
    private float nota;

    public AtividadeDTO(long id_atividade, float nu_valor_atividade, long id_diciplina, String st_nome_atividade, Date dt_data, boolean bo_tipo_atividade) {
        this.id_atividade = id_atividade;
        this.nu_valor_atividade = nu_valor_atividade;
        this.id_diciplina = id_diciplina;
        this.st_nome_atividade = st_nome_atividade;
        this.dt_data = dt_data;
        this.bo_tipo_atividade = bo_tipo_atividade;
    }

    public AtividadeDTO(){

    }

    public boolean isEntrega() {
        return entrega;
    }

    public void setEntrega(boolean entrega) {
        this.entrega = entrega;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public long getId_diciplina() {
        return id_diciplina;
    }

    public void setId_diciplina(long id_diciplina) {
        this.id_diciplina = id_diciplina;
    }

    public long getId_atividade() {
        return id_atividade;
    }

    public void setId_atividade(long id_atividade) {
        this.id_atividade = id_atividade;
    }

    public float getNu_valor_atividade() {
        return nu_valor_atividade;
    }

    public void setNu_valor_atividade(float nu_valor_atividade) {
        this.nu_valor_atividade = nu_valor_atividade;
    }

    public String getSt_nome_atividade() {
        return st_nome_atividade;
    }

    public void setSt_nome_atividade(String st_nome_atividade) {
        this.st_nome_atividade = st_nome_atividade;
    }

    public Date getDt_data() {
        return dt_data;
    }

    public void setDt_data(Date dt_data) {
        this.dt_data = dt_data;
    }

    public boolean isBo_tipo_atividade() {
        return bo_tipo_atividade;
    }

    public void setBo_tipo_atividade(boolean bo_tipo_atividade) {
        this.bo_tipo_atividade = bo_tipo_atividade;
    }

    @Override
    public String toString() {
        return "AtividadeDTO{" +
                "id_atividade=" + id_atividade +
                ", id_diciplina=" + id_diciplina +
                ", nu_valor_atividade=" + nu_valor_atividade +
                ", st_nome_atividade='" + st_nome_atividade + '\'' +
                ", dt_data=" + dt_data +
                ", bo_tipo_atividade=" + bo_tipo_atividade +
                ", entrega=" + entrega +
                ", nota=" + nota +
                '}';
    }
}

