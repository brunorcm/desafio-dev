package br.com.bycoders.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bycoders.projeto.model.Loja;
import br.com.bycoders.projeto.model.Movimento;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Long> {
	
	public List<Movimento> findByLoja(Loja loja);

}
