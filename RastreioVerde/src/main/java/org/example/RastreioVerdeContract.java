/*
 * SPDX-License-Identifier: Apache-2.0
 */
package org.example;

import org.example.classeBase.Empresa;
import org.example.classeBase.Entrega;
import org.example.classeBase.Produto;
import org.example.classeBase.RastreioVerde;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import static java.nio.charset.StandardCharsets.UTF_8;

@Contract(name = "RastreioVerdeContract",
    info = @Info(title = "RastreioVerde contract",
                description = "My Smart Contract",
                version = "0.0.1",
                license =
                        @License(name = "Apache-2.0",
                                url = ""),
                                contact =  @Contact(email = "RastreioVerde@example.com",
                                                name = "RastreioVerde",
                                                url = "http://RastreioVerde.me")))
@Default
public class RastreioVerdeContract implements ContractInterface {
    public  RastreioVerdeContract() {

    }
    @Transaction()
    public boolean rastreioVerdeExists(Context ctx, String rastreioVerdeId) {
        byte[] buffer = ctx.getStub().getState(rastreioVerdeId);
        return (buffer != null && buffer.length > 0);
    }

    
    @Transaction()
    public void createRastreioVerde(Context ctx, String rastreioVerdeId, String entregaJson, String produtoJson, String remetenteJson, String destinatarioJson) {
        boolean exists = rastreioVerdeExists(ctx,rastreioVerdeId);
        if (exists) {
            throw new RuntimeException("O id: "+rastreioVerdeId+" ja existe");
        }
        
        

        Produto produto = Produto.fromJSONString(produtoJson);
        Empresa remetente = Empresa.fromJSONString(remetenteJson);
        Empresa destinatario = Empresa.fromJSONString(destinatarioJson);
        Entrega entrega = Entrega.fromJSONString(entregaJson);

        RastreioVerde rastreioVerde = new RastreioVerde();
        
        rastreioVerde.setDestinatario(destinatario);
        rastreioVerde.setRemetenteEmpresa(remetente);
        rastreioVerde.setProduto(produto);
        rastreioVerde.setEntrega(entrega);

        ctx.getStub().putState(rastreioVerdeId, rastreioVerde.toJSONString().getBytes(UTF_8));
    }
    
    
    /*
    @Transaction()
    public void createRastreioVerde(Context ctx, String rastreioVerdeId, String descricao){
        boolean contractExists = rastreioVerdeExists(ctx, rastreioVerdeId);
        
        if(!contractExists){
            throw new RuntimeException("O contrato de id "+rastreioVerdeId+" ja existe");
        }
        
        //Produto produto = Produto.fromJSONString(produtoString);
        //Empresa destEmpresa = Empresa.fromJSONString(destinatario);
        //Empresa remetEmpresa = Empresa.fromJSONString(remetente);
        //Empresa respEmpresa = Empresa.fromJSONString(responsavel);
        
        //Empresa remetEmpresa = new Empresa("0000014444", remetente);
        //Empresa respEmpresa = new Empresa("1313131222", responsavel);
        //Empresa destEmpresa = new Empresa("0000101321", destinatario);
        //Produto produto2 = new Produto();
        //produto2.setDescricao(descricao);

        //RastreioVerde rastreioVerde = new RastreioVerde(remetEmpresa, destEmpresa);
        RastreioVerde rastreioVerde = new RastreioVerde();
        rastreioVerde.setDescricao(descricao);
        //rastreioVerde.setProduto(produto2);
        //rastreioVerde.setReponsavelTransporte(respEmpresa);
        
        ctx.getStub().putState(rastreioVerdeId, rastreioVerde.toJSONString().getBytes(UTF_8));
    }
    */
    
    
    @Transaction()
    public RastreioVerde readRastreioVerde(Context ctx, String rastreioVerdeId) {
        boolean contractExists = rastreioVerdeExists(ctx,rastreioVerdeId);
        if (!contractExists) {
            throw new RuntimeException("O contrato de id "+rastreioVerdeId+" ja existe");
        }

        RastreioVerde rastreioVerde = RastreioVerde.fromJSONString(new String(ctx.getStub().getState(rastreioVerdeId),UTF_8));
        return rastreioVerde;
    }
    
    @Transaction()
    public void updateRastreioVerde(Context ctx, String rastreioVerdeId, String entregaJson) {
        boolean exists = rastreioVerdeExists(ctx,rastreioVerdeId);
        if (!exists) {
            throw new RuntimeException("The asset "+rastreioVerdeId+" does not exist");
        }
        RastreioVerde asset = new RastreioVerde();
        Entrega entrega = Entrega.fromJSONString(entregaJson);
        asset.setEntrega(entrega);

        ctx.getStub().putState(rastreioVerdeId, asset.toJSONString().getBytes(UTF_8));
    }
    
    @Transaction()
    public void deleteRastreioVerde(Context ctx, String rastreioVerdeId) {
        boolean exists = rastreioVerdeExists(ctx,rastreioVerdeId);
        if (!exists) {
            throw new RuntimeException("The asset "+rastreioVerdeId+" does not exist");
        }
        ctx.getStub().delState(rastreioVerdeId);
    }

}
