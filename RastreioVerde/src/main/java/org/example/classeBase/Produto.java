package org.example.classeBase;

import com.owlike.genson.Genson;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

@DataType
public class Produto {
    private final static Genson genson = new Genson();
    
    @Property
    private String tipo;

    @Property
    private String purezaPorcen;


    @Property
    private String temperatura;

    @Property
    private String pressaoTanqueEmMPa;

    @Property
    private Producao producao;

    public Produto(){
    }
    
    public Produto(String tipo, String purezaPorcen, String temperatura, String pressaoTanqueEmMPa, Producao producao) {
        this.tipo = tipo;
        this.purezaPorcen = purezaPorcen;
        this.temperatura = temperatura;
        this.pressaoTanqueEmMPa = pressaoTanqueEmMPa;
        this.producao = producao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPurezaPorcen() {
        return purezaPorcen;
    }

    public void setPurezaPorcen(String purezaPorcen) {
        this.purezaPorcen = purezaPorcen;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getPressaoTanqueEmMPa() {
        return pressaoTanqueEmMPa;
    }

    public void setPressaoTanqueEmMPa(String pressaoTanqueEmMPa) {
        this.pressaoTanqueEmMPa = pressaoTanqueEmMPa;
    }

    public Producao getProducao() {
        return producao;
    }

    public void setProducao(Producao producao) {
        this.producao = producao;
    }

    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static Produto fromJSONString(String json) {
        Produto asset = genson.deserialize(json, Produto.class);
        return asset;
    }
}
