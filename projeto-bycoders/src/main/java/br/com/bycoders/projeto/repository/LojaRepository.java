package br.com.bycoders.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bycoders.projeto.model.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {
	
	Loja findByNome(String nome);

}
