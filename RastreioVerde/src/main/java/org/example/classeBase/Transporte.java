package org.example.classeBase;

import com.owlike.genson.Genson;

import org.hyperledger.fabric.contract.annotation.Property;

public class Transporte {
    private final static Genson genson = new Genson();
    
    @Property
    private String identificadorVeiculo;

    @Property
    private String emissaoPorKm;

    @Property
    private String tipoVeiculo;

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
