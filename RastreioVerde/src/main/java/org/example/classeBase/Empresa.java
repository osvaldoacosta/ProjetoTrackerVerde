package org.example.classeBase;


import com.owlike.genson.Genson;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
public class Empresa {
    private final static Genson genson = new Genson();

    @Property
    private String cnpj; //Talvez mude pra algum outro tipo de identificador

    @Property
    private String nome;


    public Empresa(String cnpj, String nome) {
        this.cnpj = cnpj;
        this.nome = nome;
    }

    public Empresa(){
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
