package br.com.senac.api.dtos;

import java.time.LocalDate;
import java.util.List;

public class ClientesRequestDto {
    private String nome;
    private String documento;
    private LocalDate dataNascimento;
    private String email;

    private List<ClienteEnderecoRequestDto> enderecos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ClienteEnderecoRequestDto> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<ClienteEnderecoRequestDto> enderecos) {
        this.enderecos = enderecos;
    }
}
