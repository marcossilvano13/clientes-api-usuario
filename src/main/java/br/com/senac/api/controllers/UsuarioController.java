package br.com.senac.api.controllers;

import br.com.senac.api.dtos.UsuariosRequestDto;
import br.com.senac.api.services.UsuariosService;
import br.com.senac.api.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuariosService usuariosService;

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody  UsuariosRequestDto usuario){
        try {
            return ResponseEntity.ok(usuariosService.login(usuario));

        }catch(RuntimeException e){

            return ResponseEntity.badRequest().body(RequestUtil.parserMensagem(e.getMessage()));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(RequestUtil.parserMensagem(e.getMessage()));
        }
    }

    @PostMapping
    @RequestMapping("/cadastro")
    public ResponseEntity<?> criar(@RequestBody UsuariosRequestDto usuario){
        try {
            return ResponseEntity.ok(usuariosService.criar(usuario));
        }catch (RuntimeException e ){
            return ResponseEntity.badRequest().body(RequestUtil.parserMensagem(e.getMessage()));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(RequestUtil.parserMensagem(e.getMessage()));
        }
    }
}
