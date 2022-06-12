package com.tcc.rastreioverdeapi.model.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class DashboardIndividual {
    @JsonProperty("co2TotalEmitido")
    private Double co2TotalEmitido;

    @JsonProperty("transportadoraMaisFrequente")
    private String transportadoraMaisFrequente;

    @JsonProperty("distanciaTotal")
    private Double distanciaTotal;

    @JsonProperty("eficienciaDaGeracao")
    private Double eficienciaDaGeracao;

    @JsonProperty("emissaoMediaPorEmpresa")
    private HashMap<String,Double> emissaoMediaPorEmpresa;

    @JsonProperty("fontesDeEnergiaUsadas")
    private HashMap<String,Integer> fontesDeEnergia;

    @JsonProperty("tiposDeVeiculosESuasQtd")
    private HashMap<String,Integer> tiposDeVeiculos;

    public DashboardIndividual() {
    }

    public DashboardIndividual(Double co2TotalEmitido, String transportadoraMaisFrequente, Double distanciaTotal, Double eficienciaDaGeracao, HashMap<String, Double> emissaoMediaPorEmpresa, HashMap<String, Integer> fontesDeEnergia, HashMap<String, Integer> tiposDeVeiculos) {
        this.co2TotalEmitido = co2TotalEmitido;
        this.transportadoraMaisFrequente = transportadoraMaisFrequente;
        this.distanciaTotal = distanciaTotal;
        this.eficienciaDaGeracao = eficienciaDaGeracao;
        this.emissaoMediaPorEmpresa = emissaoMediaPorEmpresa;
        this.fontesDeEnergia = fontesDeEnergia;
        this.tiposDeVeiculos = tiposDeVeiculos;
    }

}
