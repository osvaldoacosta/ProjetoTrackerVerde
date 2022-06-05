package org.example.classeBase;


import com.owlike.genson.Genson;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
public class Empresa {
    private final static Genson genson = new Genson();

    @Property
    private String id; 

    @Property
    private String nome;


    public Empresa(String cnpj, String nome) {
        this.id = cnpj;
        this.nome = nome;
    }

    public Empresa(){
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

    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static Empresa fromJSONString(String json) {
        Empresa asset = genson.deserialize(json, Empresa.class);
        return asset;
    }


    
}
