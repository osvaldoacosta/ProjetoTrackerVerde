package com.tcc.rastreioverdeapi.controller;

import com.tcc.rastreioverdeapi.model.Produto;
import com.tcc.rastreioverdeapi.model.RastreioVerde;

import com.tcc.rastreioverdeapi.model.RastreioVerdeCompleto;
import com.tcc.rastreioverdeapi.model.dashboard.Dashboard;
import com.tcc.rastreioverdeapi.model.dashboard.DashboardIndividual;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@Api(value = "Set of endpoints for CRUD Trade operations")
public class RastreioVerdeController {



    @GetMapping("/rastreio/")
    @ApiOperation(value = "Retorna o todos os rastreios verdes")

    private List<RastreioVerde> getAllAndReturnListOfRV() throws Exception {
        String resposta = "";

        try{
            resposta = HFJavaSDKConnection.getAllRastreioVerdes();
        }
        catch(Exception e){
            e.getMessage();
            throw e;
        }
        JSONArray jsonArray = new JSONArray(resposta);
        List<RastreioVerde> rastreioVerdeList = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject;
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException jsonException) {
                jsonException.getMessage();
                throw jsonException;
            }
            if (jsonObject != null) {
                JSONObject value = jsonObject.getJSONObject("value");
                Long key = jsonObject.getLong("key");
                RastreioVerde rv= RastreioVerde.fromJSONString(value.toString(), key);
                rastreioVerdeList.add(rv);
            }
        }
        return rastreioVerdeList;
    }
    @GetMapping("/rastreio")
    @ApiOperation(value = "Retorna todos os rastreios verdes")
    public ResponseEntity<List<RastreioVerde>> getAll(){

        List<RastreioVerde> rvList = new ArrayList<>();
        try {
            rvList = getAllAndReturnListOfRV();
        }
        catch (Exception e){
            e.getMessage();
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rvList);
    }



    @GetMapping("/rastreio/recente/{id}")
    @ApiOperation(value = "Retorna o ultimo rastreio verde")

    public ResponseEntity<RastreioVerde> getRecente(@PathVariable Long id){
        String resposta = "";

        try{
            resposta = HFJavaSDKConnection.getRastreioVerde(id);
        }catch(Exception e){
            e.getMessage();
            return ResponseEntity.notFound().build();
        };
        RastreioVerde rastreioVerde = RastreioVerde.fromJSONString(resposta, id);
        return ResponseEntity.ok().body(rastreioVerde);
    }

    private List<RastreioVerdeCompleto> getHistoryAndReturnListOfRV(Long id) throws Exception {
        String resposta = "";

        try{
            resposta = HFJavaSDKConnection.getHistory(id);
        }catch(Exception e){
            e.getMessage();
            throw e;
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
                throw jsonException;
            }
            if(jsonObject != null){
                RastreioVerdeCompleto rvCompleto = (RastreioVerdeCompleto) RastreioVerdeCompleto.fromJSONString(jsonObject.toString(), id);
                rastreioVerdeList.add(rvCompleto);
            }

        }
        return rastreioVerdeList;
    }

    @GetMapping("/rastreio/historico/{id}")
    @ApiOperation(value = "Retorna o historico do rastreio verde")
    public ResponseEntity<List<RastreioVerdeCompleto>> getHistoryAndReturnJson(@PathVariable Long id){
        List<RastreioVerdeCompleto> rvList;
        try {
            rvList = getHistoryAndReturnListOfRV(id);
        }
        catch (Exception e){
            e.getMessage();
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(rvList);
    }

    @GetMapping("/rastreio/relatorio")
    @ApiOperation(value = "Retorna informações sobre todos os contratos")
    public ResponseEntity<Dashboard> getRelatorioGeral(){
        List<List<RastreioVerdeCompleto>> rvCompletoList= new ArrayList<>();
        try {
            List<RastreioVerde> rvList = getAllAndReturnListOfRV();

            for(int i =0; i< rvList.size();i++){
                Long key = rvList.get(i).getKey();
                List<RastreioVerdeCompleto> rvCompleto = getHistoryAndReturnListOfRV(key);
                if(rvCompleto != null)
                rvCompletoList.add(rvCompleto);
            }


        }
        catch (Exception e){
            e.getMessage();

        }

        HashMap<String, Double> emissaoPorEmpresa = new HashMap<>();
        HashMap<String,Integer> quantidadePorEmpresa = new HashMap<>();
        HashMap<String, Integer> tiposDeH2 = new HashMap<>();
        HashMap<String, Integer> tiposDeVeiculos = new HashMap<>();
        HashMap<String, Integer> fontesDeEnergia = new HashMap<>();
        HashMap<String,Double> emissaoMediaPorEmpresa = new HashMap<>();

        Double energia = 0.0;
        Double volume = 0.0;
        Double co2Total = 0.0;
        Double distanciaTotal = 0.0;
        Integer qtdRv = 0;

        for(List<RastreioVerdeCompleto> rastreioVerdeList: rvCompletoList){
            //Dá pra fazer tudo com streams, porém n tenho o necessário conhecimento pra isso ._.
            rastreioVerdeList = rastreioVerdeList.stream().filter(t -> t.getEntrega().getFoiEntregue().equals(false)).collect(Collectors.toList());
            if(rastreioVerdeList != null){
                Produto produto = new Produto();
                for (RastreioVerde rastreioVerde : rastreioVerdeList){
                    String nomeEmpresa = rastreioVerde.getEntrega().getResponsavelTransporte().getNome();
                    produto = rastreioVerde.getProduto();
                    co2Total += rastreioVerde.getEntrega().getTransporte().getEmissaoPorKm()*rastreioVerde.getEntrega().getDistanciaMedia();
                    distanciaTotal += rastreioVerde.getEntrega().getDistanciaMedia();
                    volume += rastreioVerde.getProduto().getProducao().getVolumeGeradoHr();
                    energia += rastreioVerde.getProduto().getProducao().getEnergiaConsumidaEmMJ();

                    if(emissaoPorEmpresa.get(nomeEmpresa) == null){
                        emissaoPorEmpresa.put(nomeEmpresa,rastreioVerde.getEntrega().getTransporte().getEmissaoPorKm());
                    }
                    else{
                        emissaoPorEmpresa.put(nomeEmpresa,emissaoPorEmpresa.get(nomeEmpresa)+rastreioVerde.getEntrega().getTransporte().getEmissaoPorKm());
                    }
                    
                    adicionarNoHashMap(quantidadePorEmpresa, nomeEmpresa);
                    adicionarNoHashMap(tiposDeVeiculos,rastreioVerde.getEntrega().getTransporte().getTipoVeiculo());
                    qtdRv++;
                }
                adicionarNoHashMap(tiposDeH2,produto.getTipo());
                adicionarNoHashMap(fontesDeEnergia, produto.getProducao().getFonteEnergiaPProducao());
            }
            else{
                return ResponseEntity.notFound().build();
            }

        }
        Double volumeMedio = volume/qtdRv;
        Double energiaMedia = energia/qtdRv;
        emissaoPorEmpresa.forEach((k,v) -> emissaoMediaPorEmpresa.put(k,round(emissaoPorEmpresa.get(k)/quantidadePorEmpresa.get(k),2)));
        String transportadoraMaisFrequente = Collections.max(quantidadePorEmpresa.entrySet(), Map.Entry.comparingByValue()).getKey();
        String tipoDeH2MaisFrequente = Collections.max(tiposDeH2.entrySet(), Map.Entry.comparingByValue()).getKey();

        Dashboard dashboard = new Dashboard(tipoDeH2MaisFrequente,transportadoraMaisFrequente,round(co2Total,2),round(distanciaTotal,2),round(energiaMedia,2),round(volumeMedio,2),round(volumeMedio/energiaMedia,2),fontesDeEnergia,tiposDeH2,tiposDeVeiculos,emissaoMediaPorEmpresa);
        return ResponseEntity.ok().body(dashboard);
    }

    @GetMapping("/rastreio/relatorio/{id}")
    public ResponseEntity<DashboardIndividual> getRelatorioIndividual(@PathVariable Long id){
        List<RastreioVerdeCompleto> rvList;
        try {
            rvList = getHistoryAndReturnListOfRV(id).stream().filter(t -> t.getEntrega().getFoiEntregue().equals(false)).collect(Collectors.toList());
        }
        catch (Exception e){
            e.getMessage();
            return ResponseEntity.notFound().build();
        }

        Double co2Total = 0.0;
        Double distanciaTotal = 0.0;
        Integer qtdRv = 0;
        Double eficiencia = 0.0;
        HashMap<String,Integer> quantidadePorEmpresa = new HashMap<>();
        HashMap<String, Integer> tiposDeVeiculos = new HashMap<>();
        HashMap<String, Integer> fontesDeEnergia = new HashMap<>();
        HashMap<String, Double> emissaoPorEmpresa = new HashMap<>();
        HashMap<String,Double> emissaoMediaPorEmpresa = new HashMap<>();
        Produto produto = new Produto();
        for(RastreioVerdeCompleto rv : rvList){
            String nomeEmpresa = rv.getEntrega().getResponsavelTransporte().getNome();
            produto = rv.getProduto();
            co2Total += rv.getEntrega().getTransporte().getEmissaoPorKm()*rv.getEntrega().getDistanciaMedia();
            distanciaTotal += rv.getEntrega().getDistanciaMedia();
            if(emissaoPorEmpresa.get(nomeEmpresa) == null){
                emissaoPorEmpresa.put(nomeEmpresa,rv.getEntrega().getTransporte().getEmissaoPorKm());
            }
            else{
                emissaoPorEmpresa.put(nomeEmpresa,emissaoPorEmpresa.get(nomeEmpresa)+rv.getEntrega().getTransporte().getEmissaoPorKm());
            }
            adicionarNoHashMap(quantidadePorEmpresa, nomeEmpresa);
            adicionarNoHashMap(tiposDeVeiculos,rv.getEntrega().getTransporte().getTipoVeiculo());
            qtdRv++;
        }
        adicionarNoHashMap(fontesDeEnergia, produto.getProducao().getFonteEnergiaPProducao());
        eficiencia = round(produto.getProducao().getVolumeGeradoHr()/produto.getProducao().getEnergiaConsumidaEmMJ(),2);
        emissaoPorEmpresa.forEach((k,v) -> emissaoMediaPorEmpresa.put(k,round(emissaoPorEmpresa.get(k)/quantidadePorEmpresa.get(k),2)));

        String transportadoraMaisFrequente = Collections.max(quantidadePorEmpresa.entrySet(), Map.Entry.comparingByValue()).getKey();

        DashboardIndividual dashboardIndividual = new DashboardIndividual(round(co2Total,2),transportadoraMaisFrequente,round(distanciaTotal,2),eficiencia,emissaoMediaPorEmpresa,fontesDeEnergia,tiposDeVeiculos);
        return ResponseEntity.ok().body(dashboardIndividual);
    }
    private void adicionarNoHashMap(HashMap<String,Integer> map, String id) {
        if(map.get(id) == null){
            map.put(id,1);
        }
        else{
            map.put(id,map.get(id)+1);
        }
    }
    private double round(Double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
