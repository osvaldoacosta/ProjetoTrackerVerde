package org.example.classeBase;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import com.owlike.genson.Genson;

@DataType
public class Producao {
    private final static Genson genson = new Genson();


    @Property
    private String volumeGeradoHr;
    @Property
    private String energiaConsumidaEmMJ;
    @Property
    private String fonteEnergiaPProducao;

    public Producao() {
    }

  
    public String getVolumeGeradoHr() {
        return volumeGeradoHr;
    }
    public void setVolumeGeradoHr(String volumeGeradoHr) {
        this.volumeGeradoHr = volumeGeradoHr;
    }
    public String getEnergiaConsumidaEmMJ() {
        return energiaConsumidaEmMJ;
    }
    public void setEnergiaConsumidaEmMJ(String energiaConsumidaEmMJ) {
        this.energiaConsumidaEmMJ = energiaConsumidaEmMJ;
    }
    public String getFonteEnergiaPProducao() {
        return fonteEnergiaPProducao;
    }
    public void setFonteEnergiaPProducao(String fonteEnergiaPProducao) {
        this.fonteEnergiaPProducao = fonteEnergiaPProducao;
    }

    
    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static Producao fromJSONString(String json) {
        Producao asset = genson.deserialize(json, Producao.class);
        return asset;
    }

}
