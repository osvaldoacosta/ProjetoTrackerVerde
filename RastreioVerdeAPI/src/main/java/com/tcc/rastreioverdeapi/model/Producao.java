package com.tcc.rastreioverdeapi.model;

public class Producao {
    private String volumeGeradoHr;
    private String energiaConsumidaEmMJ;
    private String fonteEnergiaPProducao;

    public Producao() {
    }

    public String getVolumeGeradoHr() {
        return volumeGeradoHr;
    }
    public void setVolumeGeradoHr(String volumeGeradoHr) {
        this.volumeGeradoHr = volumeGeradoHr;
    }
    public String getEnergiaConsumidaEmMJ() {
        return energiaConsumidaEmMJ;
    }
    public void setEnergiaConsumidaEmMJ(String energiaConsumidaEmMJ) {
        this.energiaConsumidaEmMJ = energiaConsumidaEmMJ;
    }
    public String getFonteEnergiaPProducao() {
        return fonteEnergiaPProducao;
    }
    public void setFonteEnergiaPProducao(String fonteEnergiaPProducao) {
        this.fonteEnergiaPProducao = fonteEnergiaPProducao;
    }

}
