package br.com.senac.api.controllers;

import br.com.senac.api.dtos.EnderecosRequestDto;
import br.com.senac.api.entidades.Enderecos;
import br.com.senac.api.services.EndercosService;
import br.com.senac.api.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/enderecos")
public class EnderecosController {


    @Autowired
    private EndercosService endercosService;

    @GetMapping("/listar")
    public ResponseEntity<List<Enderecos>> listarTodos() {
        return ResponseEntity.ok(endercosService.listarTodos());
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody EnderecosRequestDto endereco) {
        try {
            return ResponseEntity
                    .status(201)
                    .body(endercosService.criar(endereco));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(RequestUtil.parserMensagem(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(RequestUtil.parserMensagem(e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody EnderecosRequestDto endereco
    ) {
        try {
            return ResponseEntity.ok(endercosService.atualizar(
                    id,endereco
            ));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(RequestUtil.parserMensagem(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(RequestUtil.parserMensagem(e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            endercosService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(RequestUtil.parserMensagem(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(RequestUtil.parserMensagem(e.getMessage()));
        }
    }
}
