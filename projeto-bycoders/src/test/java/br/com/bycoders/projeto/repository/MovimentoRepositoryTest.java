package br.com.bycoders.projeto.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.bycoders.projeto.ApplicationBaseTest;
import br.com.bycoders.projeto.model.Loja;
import br.com.bycoders.projeto.model.Movimento;

/**
 * Testa o repositório de movimentos
 * @author bruno
 */
public class MovimentoRepositoryTest extends ApplicationBaseTest  {
	
	@Autowired
	private MovimentoRepository movimentoRepository;
	@Autowired 
	private LojaRepository lojaRepository;
	
	@Test
	public void testObterMovimentoPorId() {
		Optional<Movimento> mov = movimentoRepository.findById(3L);
		assertTrue(mov.isPresent());
		assertEquals(mov.get().getValor(), new BigDecimal(926).setScale(2));
	}
	
	@Test
	public void testListarMovimentosPorLoja() {
		Loja loja = lojaRepository.getById(2L);
		assertNotNull(loja);
		List<Movimento> movtos = movimentoRepository.findByLojaOrderByDataOcorrenciaDescHoraOcorrenciaDesc(loja);
		assertEquals(movtos.size(), 2);
	}

}
