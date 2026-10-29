package br.com.senac.api.controllers;

import br.com.senac.api.dtos.ClienteEnderecoRequestDto;
import br.com.senac.api.dtos.ClientesRequestDto;
import br.com.senac.api.entidades.Clientes;
import br.com.senac.api.services.ClientesEnderecosService;
import br.com.senac.api.services.ClientesService;
import br.com.senac.api.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private ClientesEnderecosService clientesEnderecosService;

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listarTodos() {
        return ResponseEntity.ok(clientesService.listar());
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody ClientesRequestDto cliente) {
        try {
            return ResponseEntity
                    .status(201)
                    .body(clientesEnderecosService.criarClienteEnderecos(cliente));
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
            @RequestBody ClientesRequestDto cliente
    ) {
        try {
            return ResponseEntity.ok(clientesService.atualizar(
                    id,cliente
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
            clientesService.deletar(id);
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
