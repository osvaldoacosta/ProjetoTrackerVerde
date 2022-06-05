/*
 * SPDX-License-Identifier: Apache-2.0
 */
package org.example;



import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import static java.nio.charset.StandardCharsets.UTF_8;

import org.example.classeBase.*;

import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ledger.KeyModification;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public RastreioVerde readRastreioVerde(Context ctx, String rastreioVerdeId) {
        boolean contractExists = rastreioVerdeExists(ctx,rastreioVerdeId);
        if (!contractExists) {
            throw new RuntimeException("O contrato de id "+rastreioVerdeId+" ja existe");
        }

        RastreioVerde rastreioVerde = RastreioVerde.fromJSONString(new String(ctx.getStub().getState(rastreioVerdeId),UTF_8));
        return rastreioVerde;
    }
    @Transaction()
    public String getHistoryOfRastreioVerde(Context context, String rastreioVerdeId){
        if(rastreioVerdeId == null){
            throw new RuntimeException("O id não pode ser nulo!");
        }

        List<String> result = new ArrayList<>();
        try {
            QueryResultsIterator<KeyModification> stubHist = context.getStub().getHistoryForKey(rastreioVerdeId);

            if(stubHist == null){
                throw new ChaincodeException("Nao há historico de "+rastreioVerdeId);
            }

            Iterator<KeyModification> iterator = stubHist.iterator();

            while (iterator.hasNext()) {
                KeyModification keyModification = iterator.next();
                String value = keyModification.getStringValue().equals("") ? "{}": keyModification.getStringValue();

                String iteratorResult = "{\"timestamp\":" +"\"" +keyModification.getTimestamp() +"\"," + "\"transactionId\":"  +"\"" + keyModification.getTxId() +"\"," + "\"value\":" + value +"}";
                
                result.add(iteratorResult);

            }
            stubHist.close();
        } catch (Exception ex) {
            ex.getMessage();
            ex.getCause();
            ex.printStackTrace();

        }

        return result.toString();
    }

    @Transaction()
    public String getAllRastreioVerde(Context ctx){
        
        if (ctx == null) {
            throw new RuntimeException("O transaction context nao pode ser nulo");
        }

        List<String> rastreiosVerdes = new ArrayList<>();

        try {
            QueryResultsIterator<KeyValue> allStubs = ctx.getStub().getStateByRange("", "");

            if(allStubs == null){
                throw new ChaincodeException("Nao há nenhum contrato ");
            }

            Iterator<KeyValue> iterator = allStubs.iterator();
            
            while (iterator.hasNext()) {
                KeyValue keyValue = iterator.next();
                String key = keyValue.getKey();
                String value = keyValue.getStringValue().equals("") ? "{}": keyValue.getStringValue();
                String result = "{\"key\":" +"\"" +key +"\"," + "\"value\":" + value+"}";

                rastreiosVerdes.add(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rastreiosVerdes.toString();
    }
    


    @Transaction()
    public void createRastreioVerde(Context ctx, String rastreioVerdeId, String entregaJson, String produtoJson, String remetenteJson, String destinatarioJson) {
        boolean exists = rastreioVerdeExists(ctx,rastreioVerdeId);
        if (exists) {
            throw new RuntimeException("O id: "+rastreioVerdeId+" ja existe");
        }

        createOrUpdateRastreioVerde(ctx,rastreioVerdeId,entregaJson,produtoJson,remetenteJson,destinatarioJson);
    }

    @Transaction()
    public void updateRastreioVerde(Context ctx, String rastreioVerdeId, String entregaJson, String produtoJson, String remetenteJson, String destinatarioJson) {
        boolean exists = rastreioVerdeExists(ctx,rastreioVerdeId);
        if (!exists) {
            throw new RuntimeException("The asset "+rastreioVerdeId+" does not exist");
        }
        createOrUpdateRastreioVerde(ctx,rastreioVerdeId,entregaJson,produtoJson,remetenteJson,destinatarioJson);

    }
    private void createOrUpdateRastreioVerde(Context ctx, String rastreioVerdeId, String entregaJson, String produtoJson, String remetenteJson, String destinatarioJson) {
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


    @Transaction()
    public void deleteRastreioVerde(Context ctx, String rastreioVerdeId) {
        boolean exists = rastreioVerdeExists(ctx,rastreioVerdeId);
        if (!exists) {
            throw new RuntimeException("O id: "+rastreioVerdeId+" não existe");
        }
        ctx.getStub().delState(rastreioVerdeId);
    }

}