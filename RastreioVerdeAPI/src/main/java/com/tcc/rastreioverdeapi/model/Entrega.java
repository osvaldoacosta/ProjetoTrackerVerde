package com.tcc.rastreioverdeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Entrega {

    @JsonProperty("responsavelTransporte")
    private Empresa responsavelTransporte;

    @JsonProperty("transporte")
    private Transporte transporte;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("localEntrega")
    private String localEntrega;

    @JsonProperty("localRecebimento")
    private String localRecebimento;

    @JsonProperty("distanciaMedia")
    private Integer distanciaMedia;

    @JsonProperty("foiEntregue")
    private Boolean foiEntregue;


    public Entrega() {
    }

    public Entrega(Empresa responsavelTransporte, Transporte tipoTransporte, String descricaoEntrega, String localEntrega, String localRecebimento, Integer distanciaMedia, Boolean foiEntregue) {
        this.responsavelTransporte = responsavelTransporte;
        this.transporte = tipoTransporte;
        this.descricao = descricaoEntrega;
        this.localEntrega = localEntrega;
        this.localRecebimento = localRecebimento;
        this.distanciaMedia = distanciaMedia;
        this.foiEntregue = foiEntregue;
    }

    public Empresa getResponsavelTransporte() {
        return responsavelTransporte;
    }

    public void setResponsavelTransporte(Empresa responsavelTransporte) {
        this.responsavelTransporte = responsavelTransporte;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Boolean getFoiEntregue() {
        return foiEntregue;
    }

    public void setFoiEntregue(Boolean foiEntregue) {
        this.foiEntregue = foiEntregue;
    }
}
