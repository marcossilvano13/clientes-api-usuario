package br.com.senac.api.services;

import br.com.senac.api.dtos.ClientesRequestDto;
import br.com.senac.api.entidades.Clientes;
import br.com.senac.api.repositorios.ClientesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepositorio clientesRepositorio;

    public List<Clientes> listar() {
        return clientesRepositorio.findAll();
    }

    public Clientes criar(ClientesRequestDto cliente) {
        Clientes clienteSaida = this.clientesRequestDtoToClientes(cliente);

        return clientesRepositorio.save(clienteSaida);
    }

    public Clientes atualizar(Long id, ClientesRequestDto cliente) {
        if(clientesRepositorio.existsById(id)) {
            Clientes clienteSaida = this.clientesRequestDtoToClientes(cliente);
            clienteSaida.setId(id);

            return clientesRepositorio.save(clienteSaida);
        }

        throw new RuntimeException("Cliente não encontrado!");
    }

    public void deletar(Long id) {
        if(clientesRepositorio.existsById(id)) {
            clientesRepositorio.deleteById(id);
            return;
        }

        throw new RuntimeException("Cliente não encontrado!");
    }

    public Clientes listarPorId(Long id){
        Optional<Clientes> clienteRetorno = clientesRepositorio.findById(id);
        if(clienteRetorno.isPresent()){
            return clienteRetorno.get();
        }

        throw new RuntimeException("Cliente não encontrado!");
    }

    private Clientes clientesRequestDtoToClientes(ClientesRequestDto entrada) {
        Clientes saida = new Clientes();

        saida.setNome(entrada.getNome());
        saida.setDocumento(entrada.getDocumento());
        saida.setEmail(entrada.getEmail());
        saida.setDataNascimento(entrada.getDataNascimento());

        return saida;
    }
}
