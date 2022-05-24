package com.tcc.rastreioverdeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

public class Entrega {

    @JsonProperty("responsavelTransporte")
    private Empresa responsavelTransporte;

    @JsonProperty("tipoTransporte")
    private Transporte tipoTransporte;

    @JsonProperty("descricaoEntrega")
    private String descricaoEntrega;

    @JsonProperty("localEntrega")
    private String localEntrega;

    @JsonProperty("localRecebimento")
    private String localRecebimento;

    @JsonProperty("distanciaMedia")
    private Integer distanciaMedia;


    public Entrega() {
    }

    public Entrega(Empresa responsavelTransporte, Transporte tipoTransporte, String descricaoEntrega, String localEntrega, String localRecebimento, Integer distanciaMedia) {
        this.responsavelTransporte = responsavelTransporte;
        this.tipoTransporte = tipoTransporte;
        this.descricaoEntrega = descricaoEntrega;
        this.localEntrega = localEntrega;
        this.localRecebimento = localRecebimento;
        this.distanciaMedia = distanciaMedia;
    }

    public Empresa getResponsavelTransporte() {
        return responsavelTransporte;
    }

    public void setResponsavelTransporte(Empresa responsavelTransporte) {
        this.responsavelTransporte = responsavelTransporte;
    }

    public Transporte getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(Transporte tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    public String getDescricaoEntrega() {
        return descricaoEntrega;
    }

    public void setDescricaoEntrega(String descricaoEntrega) {
        this.descricaoEntrega = descricaoEntrega;
    }

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public String getLocalRecebimento() {
        return localRecebimento;
    }

    public void setLocalRecebimento(String localRecebimento) {
        this.localRecebimento = localRecebimento;
    }

    public Integer getDistanciaMedia() {
        return distanciaMedia;
    }

    public void setDistanciaMedia(Integer distanciaMedia) {
        this.distanciaMedia = distanciaMedia;
    }


}
