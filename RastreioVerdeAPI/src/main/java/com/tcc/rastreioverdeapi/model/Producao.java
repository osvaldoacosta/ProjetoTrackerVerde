package com.tcc.rastreioverdeapi.model;

public class Producao {
    private Double volumeGeradoHr;
    private Double energiaConsumidaEmMJ;
    private String fonteEnergiaPProducao;

    public Producao() {
    }

    public Double getVolumeGeradoHr() {
        return volumeGeradoHr;
    }
    public void setVolumeGeradoHr(Double volumeGeradoHr) {
        this.volumeGeradoHr = volumeGeradoHr;
    }
    public Double getEnergiaConsumidaEmMJ() {
        return energiaConsumidaEmMJ;
    }
    public void setEnergiaConsumidaEmMJ(Double energiaConsumidaEmMJ) {
        this.energiaConsumidaEmMJ = energiaConsumidaEmMJ;
    }
    public String getFonteEnergiaPProducao() {
        return fonteEnergiaPProducao;
    }
    public void setFonteEnergiaPProducao(String fonteEnergiaPProducao) {
        this.fonteEnergiaPProducao = fonteEnergiaPProducao;
    }

}
