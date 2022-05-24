package com.tcc.rastreioverdeapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Produto {
    @JsonProperty("descricao")
    private String descricao;

    public Produto() {
    }

    public Produto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
