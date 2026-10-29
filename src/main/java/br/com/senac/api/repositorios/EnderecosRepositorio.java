package br.com.senac.api.repositorios;

import br.com.senac.api.entidades.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecosRepositorio extends JpaRepository<Enderecos, Long> {
}
