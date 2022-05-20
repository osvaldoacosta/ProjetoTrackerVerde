/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.example.classeBase;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import com.owlike.genson.Genson;

@DataType()
public class RastreioVerde {

    private final static Genson genson = new Genson();

    @Property
    private Produto produto;

    @Property
    private Entrega entrega;

    @Property
    private Empresa remetenteEmpresa;

    @Property
    private Empresa destinatario;

    public RastreioVerde() {
    }

    public RastreioVerde(Empresa remetenteEmpresa, Empresa destinatario) {
        this.remetenteEmpresa = remetenteEmpresa;
        this.destinatario = destinatario;
    }

    public void setRemetenteEmpresa(Empresa remetenteEmpresa) {
        this.remetenteEmpresa = remetenteEmpresa;
    }

    public void setDestinatario(Empresa destinatario) {
        this.destinatario = destinatario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }


    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Empresa getRemetenteEmpresa() {
        return remetenteEmpresa;
    }

    public Empresa getDestinatario() {
        return destinatario;
    }

    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static RastreioVerde fromJSONString(String json) {
        RastreioVerde asset = genson.deserialize(json, RastreioVerde.class);
        return asset;
    }
}
