package com.tcc.rastreioverdeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.owlike.genson.Genson;
import org.json.JSONObject;

public class RastreioVerde {
    private final static Genson genson = new Genson();

    @JsonProperty("key")
    private Long key;

    @JsonProperty("entrega")
    private Entrega entrega;

    @JsonProperty("produto")
    private Produto produto;

    @JsonProperty("remetente")
    private Empresa remetente;

    @JsonProperty("destinatario")
    private Empresa destinatario;

    public RastreioVerde() {
    }

    public RastreioVerde(String id, Entrega entrega, Produto produto, Empresa remetente, Empresa destinatario) {
        this.key = Long.parseLong(id);
        this.entrega = entrega;
        this.produto = produto;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }

    public RastreioVerde(Long id, Entrega entrega, Produto produto, Empresa remetente, Empresa destinatario) {
        this.key = id;
        this.entrega = entrega;
        this.produto = produto;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }

    public Long getKey() {
        return key;
    }
    public void setKey(Long key) {
        this.key = key;
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

    public static RastreioVerde fromJSONString(String json, Long id)  {


        JSONObject jsonObject = new JSONObject(json);


        Entrega entrega = genson.deserialize(jsonObject.get("entrega").toString(), Entrega.class);
        Produto produto = genson.deserialize(jsonObject.get("produto").toString(), Produto.class);
        Empresa remetente = genson.deserialize(jsonObject.get("remetenteEmpresa").toString(), Empresa.class);
        Empresa destinatario = genson.deserialize(jsonObject.get("destinatario").toString(), Empresa.class);


        return new RastreioVerde(id,entrega,produto,remetente,destinatario);

    }



}

