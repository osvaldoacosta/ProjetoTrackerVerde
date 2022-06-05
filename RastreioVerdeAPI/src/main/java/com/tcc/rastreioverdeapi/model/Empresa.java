package com.tcc.rastreioverdeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

public class Empresa {
    @JsonProperty("id")
    private String id;

    @JsonProperty("nome")
    private String nome;

    public Empresa() {
    }

    public Empresa(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static Empresa fromJSONString(String json)  {

        JSONObject jsonObject = new JSONObject(json);

        Empresa asset = new Empresa(jsonObject.getString("id"), jsonObject.getString("nome"));

        return asset;

    }
}
