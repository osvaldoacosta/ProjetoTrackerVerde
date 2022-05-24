package com.tcc.rastreioverdeapi.controller;

import com.tcc.rastreioverdeapi.model.Empresa;
import com.tcc.rastreioverdeapi.model.RastreioVerde;

import com.tcc.rastreioverdeapi.service.HFJavaSDKBasicExample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "Set of endpoints for CRUD Trade operations")
public class RastreioVerdeController {

  /*
    @GetMapping("/contratos")
    @ApiOperation(value = "Get all contratos", nickname = "getAll")
    public ResponseEntity<List<Empresa>> getAll() {
        List<Empresa> empresas = new ArrayList<>();
        Empresa rastreioVerde = new Empresa("013131", "Oi");
        Empresa empresa = new Empresa("1455151", "TIm");
        empresas.add(empresa);
        empresas.add(rastreioVerde);

        return new ResponseEntity<List<Empresa>>(empresas, HttpStatus.OK);
    }
*/

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna Transporte")
    public Object getTransporte(@PathVariable Long id){
        String transporte = "";

        try{
            transporte = HFJavaSDKBasicExample.getSoybeans(id);
        }catch(Exception e){
            return e.getMessage();
        };
        RastreioVerde rastreioVerde = RastreioVerde.fromJSONString(transporte);
        return rastreioVerde;
    }

/*
    public Soybeans getSoybeans(@PathVariable Long id) {

        String soybeans  = "";
        try {soybeans = HFJavaSDKBasicExample.getSoybeans(id);} catch(Exception e){};

        System.out.print(soybeans);
        Soybeans newAsset = Soybeans.fromJSONString(soybeans,id);

        return newAsset;
    }

 */
}
