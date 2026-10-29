package br.com.senac.api.services;

import br.com.senac.api.dtos.EnderecosRequestDto;
import br.com.senac.api.entidades.Cidades;
import br.com.senac.api.entidades.Clientes;
import br.com.senac.api.entidades.Enderecos;
import br.com.senac.api.repositorios.EnderecosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndercosService {

    @Autowired
    private EnderecosRepositorio enderecosRepositorio;

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private CidadesService cidadesService;


    public Enderecos criar(EnderecosRequestDto endereco) {
        Clientes cliente = clientesService.listarPorId(endereco.getClienteId());

        Enderecos enderecoPersist = this.enderecosRequestDtoToEnderecos(endereco);
        enderecoPersist.setCliente(cliente);

        return enderecosRepositorio.save(enderecoPersist);
    }

    public  Enderecos atualizar(Long id, EnderecosRequestDto endereco) {
        if(enderecosRepositorio.existsById(id)) {
            Clientes cliente = clientesService.listarPorId(endereco.getClienteId());
            Enderecos enderecoPersist = this.enderecosRequestDtoToEnderecos(endereco);
            enderecoPersist.setId(id);

            enderecoPersist.setCliente(cliente);

            return enderecosRepositorio.save(enderecoPersist);
        }

        throw new RuntimeException("Endereço não encontrado!");
    }

    public void deletar(Long id) {
        if(enderecosRepositorio.existsById(id)) {
            enderecosRepositorio.deleteById(id);
            return;
        }

        throw new RuntimeException("Endereço não encontrado!");
    }

    public List<Enderecos> listarTodos() {
        return enderecosRepositorio.findAll();
    }

    private Enderecos enderecosRequestDtoToEnderecos(EnderecosRequestDto entrada) {
        Enderecos saida = new Enderecos();
        saida.setBairro(entrada.getBairro());
        saida.setCep(entrada.getCep());
        saida.setComplemento(entrada.getComplemento());
        saida.setRua(entrada.getRua());

        Cidades cidadeRetorno = cidadesService.listarPorId(entrada.getCidadeId());
        saida.setCidade(cidadeRetorno);


        return saida;
    }
}
