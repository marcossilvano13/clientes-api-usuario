package br.com.senac.api.repositorios;

import br.com.senac.api.entidades.Cidades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadesRepositorio extends JpaRepository<Cidades, Long> {
}
