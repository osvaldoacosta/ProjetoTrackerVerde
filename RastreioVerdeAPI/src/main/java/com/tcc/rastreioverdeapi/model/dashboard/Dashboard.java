package com.tcc.rastreioverdeapi.model.dashboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;

public class Dashboard extends DashboardIndividual{
    @JsonProperty("h2MaisSolicitado")
    private String tipoH2MaisSolicitado;

    @JsonProperty("tiposEQtdDeH2Transportados")
    private HashMap<String,Integer> tiposDeH2;

    @JsonProperty("energiaMediaGasta")
    private Double energiaMediaGasta;
    @JsonProperty("volumeMedioH2")
    private Double volumeMedioH2;



    public Dashboard(String tipoH2MaisSolicitado, String transportadoraMaisFrequente, Double co2TotalEmitido, Double distanciaTotal, Double energiaMediaGasta, Double volumeMedioH2, Double eficienciaDaGeracao, HashMap<String, Integer> fontesDeEnergia, HashMap<String, Integer> tiposDeH2, HashMap<String, Integer> tiposDeVeiculos, HashMap<String, Double> emissaoMediaPorEmpresa) {
        super(co2TotalEmitido,transportadoraMaisFrequente,distanciaTotal,eficienciaDaGeracao,emissaoMediaPorEmpresa,fontesDeEnergia,tiposDeVeiculos);
        this.tipoH2MaisSolicitado = tipoH2MaisSolicitado;

        this.energiaMediaGasta = energiaMediaGasta;
        this.volumeMedioH2 = volumeMedioH2;

        this.tiposDeH2 = tiposDeH2;

    }


}
