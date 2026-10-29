package br.com.senac.api.services;

import br.com.senac.api.dtos.TokenResponseDto;
import br.com.senac.api.dtos.UsuariosRequestDto;
import br.com.senac.api.entidades.Usuarios;
import br.com.senac.api.jwt.JwtService;
import br.com.senac.api.repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepositorio usuariosRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public Usuarios criar(UsuariosRequestDto usuario){
        Usuarios usuarioPersit = this.usuariosRequestDtoParaUsuarios(usuario);

        return usuariosRepositorio.save(usuarioPersit);

    }

    public TokenResponseDto login(UsuariosRequestDto usuario){
        Optional<Usuarios> usuariosRetorno = usuariosRepositorio.findByEmail(usuario.getEmail());
        if (usuariosRetorno.isPresent()){
            Usuarios usuarioDb = usuariosRetorno.get();

            if (passwordEncoder.matches(usuario.getSenha(),usuarioDb.getSenha())) {
                String token = jwtService.gerarToken(usuarioDb);

                return new TokenResponseDto(token);
            }

            throw new RuntimeException("Senha Inválida!");

        }

        throw new RuntimeException("Usuario não Encontrado!");
    }

    private Usuarios usuariosRequestDtoParaUsuarios(UsuariosRequestDto entrada){
        Usuarios saida = new Usuarios();
        saida.setNome(entrada.getNome());
        saida.setEmail(entrada.getEmail());
        saida.setSenha(passwordEncoder.encode(entrada.getSenha()));
        return saida;
    }
}
