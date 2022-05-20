/*
 * SPDX-License-Identifier: Apache License 2.0
 */

package org.example;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;

import org.example.classeBase.Produto;
import org.example.classeBase.RastreioVerde;
import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public final class RastreioVerdeContractTest {

    @Nested
    class AssetExists {
        @Test
        public void noProperAsset() {

            RastreioVerdeContract contract = new  RastreioVerdeContract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);

            when(stub.getState("10001")).thenReturn(new byte[] {});
            boolean result = contract.rastreioVerdeExists(ctx,"10001");

            assertFalse(result);
        }

        @Test
        public void assetExists() {

            RastreioVerdeContract contract = new  RastreioVerdeContract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);

            when(stub.getState("10001")).thenReturn(new byte[] {42});
            boolean result = contract.rastreioVerdeExists(ctx,"10001");

            assertTrue(result);

        }

        @Test
        public void noKey() {
            RastreioVerdeContract contract = new  RastreioVerdeContract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);

            when(stub.getState("10002")).thenReturn(null);
            boolean result = contract.rastreioVerdeExists(ctx,"10002");

            assertFalse(result);

        }

    }

    @Nested
    class AssetCreates {
        //TODO resolver os testes
        
        
        
        @Test
        public void newAssetCreate() {
            RastreioVerdeContract contract = new  RastreioVerdeContract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);

            String json = "{\"value\":\"TheRastreioVerde\"}";

            contract.createRastreioVerde(ctx, "", "10001", "{}", "{}", "{}");

            verify(stub).putState("10001", json.getBytes(UTF_8));
        }

        @Test
        public void alreadyExists() {
            RastreioVerdeContract contract = new  RastreioVerdeContract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);

            when(stub.getState("10002")).thenReturn(new byte[] { 42 });

            Exception thrown = assertThrows(RuntimeException.class, () -> {
                contract.createRastreioVerde(ctx, "10002", "", "{}","{}", "{}");
            });

            assertEquals(thrown.getMessage(), "The asset 10002 already exists");

        }
    
    }

    @Test
    public void assetRead() {
        RastreioVerdeContract contract = new  RastreioVerdeContract();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);

        RastreioVerde asset = new  RastreioVerde();
        Produto prod = new Produto("Produto top");
        asset.setProduto(prod);

        String json = asset.toJSONString();
        when(stub.getState("10001")).thenReturn(json.getBytes(StandardCharsets.UTF_8));

        RastreioVerde returnedAsset = contract.readRastreioVerde(ctx, "10001");
        assertEquals(returnedAsset.getProduto(), asset.getProduto());
    }

    @Nested
    class AssetUpdates {
        @Test
        public void updateExisting() {
            RastreioVerdeContract contract = new  RastreioVerdeContract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);
            when(stub.getState("10001")).thenReturn(new byte[] { 42 });

            contract.updateRastreioVerde(ctx, "10001", "updates");

            String json = "{\"value\":\"updates\"}";
            verify(stub).putState("10001", json.getBytes(UTF_8));
        }

        @Test
        public void updateMissing() {
            RastreioVerdeContract contract = new  RastreioVerdeContract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);

            when(stub.getState("10001")).thenReturn(null);

            Exception thrown = assertThrows(RuntimeException.class, () -> {
                contract.updateRastreioVerde(ctx, "10001", "TheRastreioVerde");
            });

            assertEquals(thrown.getMessage(), "The asset 10001 does not exist");
        }
    
    }

    @Test
    public void assetDelete() {
        RastreioVerdeContract contract = new  RastreioVerdeContract();
        Context ctx = mock(Context.class);
        ChaincodeStub stub = mock(ChaincodeStub.class);
        when(ctx.getStub()).thenReturn(stub);
        when(stub.getState("10001")).thenReturn(null);

        Exception thrown = assertThrows(RuntimeException.class, () -> {
            contract.deleteRastreioVerde(ctx, "10001");
        });

        assertEquals(thrown.getMessage(), "The asset 10001 does not exist");
    }

}
