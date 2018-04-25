package com.tcc.secretaria.DTO;

public class DisciplinaListDTO {
    private Long nuId;
    private String stNomeD;
    private String stNomeP;
    private int nuCargaH;
    private String stPeriodo;

    public DisciplinaListDTO(Long nuId, String stNomeD, String stNomeP, int nuCargaH, String stPeriodo) {
        this.nuId = nuId;
        this.stNomeD = stNomeD;
        this.stNomeP = stNomeP;
        this.nuCargaH = nuCargaH;
        this.stPeriodo = stPeriodo;
    }

    public Long getNiId() {
        return this.nuId;
    }

    public void setId(Long id) {
        this.nuId = id;
    }

    public String getStNomeD() {
        return stNomeD;
    }

    public void setStNomeD(String stNomeD) {
        this.stNomeD = stNomeD;
    }

    public String getStNomeP() {
        return stNomeP;
    }

    public void setStNomeP(String stNomeP) {
        this.stNomeP = stNomeP;
    }

    public int getNuCargaH() {
        return nuCargaH;
    }

    public void setNuCargaH(int nuCargaH) {
        this.nuCargaH = nuCargaH;
    }

    public String getStPeriodo() {
        return stPeriodo;
    }

    public void setStPeriodo(String stPeriodo) {
        this.stPeriodo = stPeriodo;
    }
}
