package com.tcc.rastreioverdeapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Produto {

    @JsonProperty("tipo")
    private String tipo;
    @JsonProperty("purezaPorcen")
    private String purezaPorcen;
    @JsonProperty("temperatura")
    private String temperatura;
    @JsonProperty("pressaoTanqueEmMPa")
    private String pressaoTanqueEmMPa;
    @JsonProperty("producao")
    private Producao producao;

    public Produto(){
    }

    public Produto(String tipo, String purezaPorcen, String temperatura, String pressaoTanqueEmMPa, Producao producao) {
        this.tipo = tipo;
        this.purezaPorcen = purezaPorcen;
        this.temperatura = temperatura;
        this.pressaoTanqueEmMPa = pressaoTanqueEmMPa;
        this.producao = producao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPurezaPorcen() {
        return purezaPorcen;
    }

    public void setPurezaPorcen(String purezaPorcen) {
        this.purezaPorcen = purezaPorcen;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getPressaoTanqueEmMPa() {
        return pressaoTanqueEmMPa;
    }

    public void setPressaoTanqueEmMPa(String pressaoTanqueEmMPa) {
        this.pressaoTanqueEmMPa = pressaoTanqueEmMPa;
    }

    public Producao getProducao() {
        return producao;
    }

    public void setProducao(Producao producao) {
        this.producao = producao;
    }

}
