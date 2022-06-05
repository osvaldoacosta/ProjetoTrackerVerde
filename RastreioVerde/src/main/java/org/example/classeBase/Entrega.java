package org.example.classeBase;

import com.owlike.genson.Genson;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
public class Entrega {
    private final static Genson genson = new Genson();

    @Property
    private Empresa responsavelTransporte;
    
    @Property
    private Transporte transporte;

    @Property
    private String descricaoEntrega;

    @Property
    private String localEntrega;

    @Property
    private String localRecebimento;
    
    @Property
    private Integer distanciaMedia;
    
    @Property
    private Boolean foiEntregue;
    
    public Boolean getFoiEntregue() {
        return foiEntregue;
    }

    public void setFoiEntregue(Boolean foiEntregue) {
        this.foiEntregue = foiEntregue;
    }

    public Entrega() {
    }

    public Empresa getResponsavelTransporte() {
        return responsavelTransporte;
    }

    public void setResponsavelTransporte(Empresa responsavelTransporte) {
        this.responsavelTransporte = responsavelTransporte;
    }

    public String getLocalEntrega() {
        return localEntrega;
    }

    public void setLocalEntrega(String localEntrega) {
        this.localEntrega = localEntrega;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public String getDescricao() {
        return descricaoEntrega;
    }

    public void setDescricao(String descricao) {
        this.descricaoEntrega = descricao;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public Integer getDistanciaMedia() {
        return distanciaMedia;
    }

    public void setDistanciaMedia(Integer distanciaMedia) {
        this.distanciaMedia = distanciaMedia;
    }

    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public String getLocalRecebimento() {
        return localRecebimento;
    }

    public void setLocalRecebimento(String localRecebimento) {
        this.localRecebimento = localRecebimento;
    }

    public static Entrega fromJSONString(String json) {
        Entrega asset = genson.deserialize(json, Entrega.class);
        return asset;
    }
    
}
