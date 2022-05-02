package br.com.bycoders.projeto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bycoders.projeto.model.Loja;

@Repository
public interface LojaRepository extends CrudRepository<Loja, Long> {
	
	Loja findByNome(String nome);

}
