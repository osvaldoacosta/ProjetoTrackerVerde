package com.tcc.rastreioverdeapi.controller;

import com.tcc.rastreioverdeapi.model.RastreioVerde;

import com.tcc.rastreioverdeapi.model.RastreioVerdeCompleto;
import com.tcc.rastreioverdeapi.service.HFJavaSDKConnection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@Api(value = "Set of endpoints for CRUD Trade operations")
public class RastreioVerdeController {



    @GetMapping("/rastreio/")
    @ApiOperation(value = "Retorna o todos os rastreios verdes")

    public ResponseEntity<List<RastreioVerde>> getAll(){
        String resposta = "";

        try{
            resposta = HFJavaSDKConnection.getAllRastreioVerdes();
        }
        catch(Exception e){
            e.getMessage();
            return ResponseEntity.notFound().build();
        }
        JSONArray jsonArray = new JSONArray(resposta);
        List<RastreioVerde> rastreioVerdeList = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException jsonException) {
                jsonException.getMessage();
            }
            if (jsonObject != null) {
                JSONObject value = jsonObject.getJSONObject("value");
                Long key = jsonObject.getLong("key");
                RastreioVerde rv= RastreioVerde.fromJSONString(value.toString(), key);
                rastreioVerdeList.add(rv);
            }
        }
        return ResponseEntity.ok(rastreioVerdeList);
    }



    @GetMapping("/rastreio/recente/{id}")
    @ApiOperation(value = "Retorna o ultimo rastreio verde")

    public ResponseEntity<RastreioVerde> getRecente(@PathVariable Long id){
        String transporte = "";

        try{
            transporte = HFJavaSDKConnection.getRastreioVerde(id);
        }catch(Exception e){
            e.getMessage();
            return ResponseEntity.notFound().build();
        };
        RastreioVerde rastreioVerde = RastreioVerde.fromJSONString(transporte, id);
        return ResponseEntity.ok().body(rastreioVerde);
    }

    @GetMapping("/rastreio/historico/{id}")
    @ApiOperation(value = "Retorna o historico do rastreio verde")
    public ResponseEntity<List<RastreioVerdeCompleto>> getHistory(@PathVariable Long id){
        String resposta = "";

        try{
            resposta = HFJavaSDKConnection.getHistory(id);
        }catch(Exception e){
            e.getMessage();
            return ResponseEntity.notFound().build();
        };
        JSONArray jsonArray = new JSONArray(resposta);
        List<RastreioVerdeCompleto> rastreioVerdeList = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
            }
            catch (JSONException jsonException){
                jsonException.getMessage();
                return ResponseEntity.notFound().build();
            }
            if(jsonObject != null){
                RastreioVerdeCompleto rvCompleto = (RastreioVerdeCompleto) RastreioVerdeCompleto.fromJSONString(jsonObject.toString(), id);
                rastreioVerdeList.add(rvCompleto);
            }

        }



        return ResponseEntity.ok().body(rastreioVerdeList);
    }
}
