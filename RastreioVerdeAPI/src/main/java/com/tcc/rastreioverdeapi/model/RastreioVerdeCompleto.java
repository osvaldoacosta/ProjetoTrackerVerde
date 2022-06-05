package com.tcc.rastreioverdeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.owlike.genson.Genson;
import org.json.JSONObject;

public class RastreioVerdeCompleto extends RastreioVerde{
    private final static Genson genson = new Genson();

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("transactionId")
    private String transactionId;

    public RastreioVerdeCompleto(String key, Entrega entrega, Produto produto, Empresa remetente, Empresa destinatario, String timestamp, String transactionId) {
        super(key, entrega, produto, remetente, destinatario);
        this.timestamp = timestamp;
        this.transactionId = transactionId;
    }

    public RastreioVerdeCompleto(Long key, Entrega entrega, Produto produto, Empresa remetente, Empresa destinatario, String timestamp, String transactionId) {
        super(key, entrega, produto, remetente, destinatario);
        this.timestamp = timestamp;
        this.transactionId = transactionId;
    }


    public static RastreioVerde fromJSONString(String json, Long id)  {

        JSONObject jsonObject = new JSONObject(json);

        JSONObject value = jsonObject.getJSONObject("value");

        String timestamp = jsonObject.getString("timestamp");
        String transactionId = jsonObject.getString("transactionId");

        if(!value.isEmpty()) {
            Entrega entrega = genson.deserialize(value.get("entrega").toString(), Entrega.class);
            Produto produto = genson.deserialize(value.get("produto").toString(), Produto.class);
            Empresa remetente = genson.deserialize(value.get("remetenteEmpresa").toString(), Empresa.class);
            Empresa destinatario = genson.deserialize(value.get("destinatario").toString(), Empresa.class);

            return new RastreioVerdeCompleto(id, entrega, produto, remetente, destinatario, timestamp, transactionId);
        }

        return new RastreioVerdeCompleto(id, null, null, null, null, timestamp, transactionId);



    }
}
