package com.tcc.rastreioverdeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transporte {
    @JsonProperty("identificadorVeiculo")
    private String identificadorVeiculo;

    @JsonProperty("emissaoPorKm")
    private String emissaoPorKm;

    @JsonProperty("tipoVeiculo")
    private String tipoVeiculo;

    public Transporte(String identificadorVeiculo, String emissaoPorKm, String tipoVeiculo) {
        this.identificadorVeiculo = identificadorVeiculo;
        this.emissaoPorKm = emissaoPorKm;
        this.tipoVeiculo = tipoVeiculo;
    }

    public Transporte() {
    }

    public String getIdentificadorVeiculo() {
        return identificadorVeiculo;
    }

    public void setIdentificadorVeiculo(String identificadorVeiculo) {
        this.identificadorVeiculo = identificadorVeiculo;
    }

    public String getEmissaoPorKm() {
        return emissaoPorKm;
    }

    public void setEmissaoPorKm(String emissaoPorKm) {
        this.emissaoPorKm = emissaoPorKm;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    
}
