package com.tcc.rastreioverdeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.owlike.genson.Genson;
import org.json.JSONObject;

public class RastreioVerde {
    private final static Genson genson = new Genson();

    @JsonProperty("rastreioVerdeId")
    private Long id;

    @JsonProperty("entregaJson")
    private Entrega entrega;

    @JsonProperty("produtoJson")
    private Produto produto;

    @JsonProperty("remetenteJson")
    private Empresa remetente;

    @JsonProperty("destinatarioJson")
    private Empresa destinatario;

    public RastreioVerde() {
    }

    public RastreioVerde(String id, Entrega entrega, Produto produto, Empresa remetente, Empresa destinatario) {
        this.id = Long.parseLong(id);
        this.entrega = entrega;
        this.produto = produto;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }

    public RastreioVerde(Long id, Entrega entrega, Produto produto, Empresa remetente, Empresa destinatario) {
        this.id = id;
        this.entrega = entrega;
        this.produto = produto;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }

    public Long getId() {
        return id;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public Produto getProduto() {
        return produto;
    }

    public Empresa getRemetente() {
        return remetente;
    }

    public Empresa getDestinatario() {
        return destinatario;
    }

    public static RastreioVerde fromJSONString(String json, Long sid)  {
        /*
        long id = sid;
        String idString = new JSONObject(json).getString("rastreioVerdeId");
        String entrega = new JSONObject(json).getString("entregaJson");

        String farm = new JSONObject(json).getString("remetenteJson");
        String destinatarioJson = new JSONObject(json).getString("destinatarioJson");


        RastreioVerde asset = new RastreioVerde(id, farm, harvest_date);
        */
        return null;

    }
}

