package br.com.bycoders.projeto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.bycoders.projeto.model.Loja;
import br.com.bycoders.projeto.model.Movimento;

@Repository
public interface MovimentoRepository extends CrudRepository<Movimento, Long> {
	
	public List<Movimento> findByLoja(Loja loja);

}
