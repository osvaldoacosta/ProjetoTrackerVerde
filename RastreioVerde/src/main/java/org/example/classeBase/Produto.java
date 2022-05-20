package org.example.classeBase;

import com.owlike.genson.Genson;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
public class Produto {
    private final static Genson genson = new Genson();
    
    @Property
    private String descricao;

    public Produto(){
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

    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static Produto fromJSONString(String json) {
        Produto asset = genson.deserialize(json, Produto.class);
        return asset;
    }
}
