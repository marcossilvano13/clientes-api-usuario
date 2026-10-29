package br.com.senac.api.controllers;

import br.com.senac.api.dtos.CidadesRequestDto;
import br.com.senac.api.entidades.Cidades;
import br.com.senac.api.services.CidadesService;
import br.com.senac.api.utils.RequestUtil;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cidades")
public class CidadesController {

    @Autowired
    private CidadesService cidadesService;

    @GetMapping("/listar")
    public ResponseEntity<List<Cidades>> listarTodos(){
        return ResponseEntity.ok(cidadesService.listarTodos());
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody CidadesRequestDto cidade){
        try{
            return ResponseEntity.status(201).body(cidadesService.criar(cidade));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(RequestUtil.parserMensagem(e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(RequestUtil.parserMensagem(e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody CidadesRequestDto cidade){
        try{
            return ResponseEntity.ok(cidadesService.atualizar(id, cidade));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(RequestUtil.parserMensagem(e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(RequestUtil.parserMensagem(e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable  Long id){
        try{
            cidadesService.deletar(id);
            return ResponseEntity.ok(null);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(RequestUtil.parserMensagem(e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(RequestUtil.parserMensagem(e.getMessage()));
        }
    }


}
