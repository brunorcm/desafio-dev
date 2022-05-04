package br.com.bycoders.projeto.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.bycoders.projeto.ApplicationBaseTest;
import br.com.bycoders.projeto.model.Loja;

/**
 * Testa o repositório de loja
 * @author bruno
 */
public class LojaRepositoryTest extends ApplicationBaseTest {
	
	@Autowired
	private LojaRepository lojaRepository;
	
	@Test
	public void testObterLoja() {
		Optional<Loja> loja = lojaRepository.findById(1L);
		assertNotNull(loja.get());
	}
	
	@Test
	public void testFindLojaByNome() {
		Loja loja = lojaRepository.findByNome("LOJA1");
		assertNotNull(loja);
	}

}
