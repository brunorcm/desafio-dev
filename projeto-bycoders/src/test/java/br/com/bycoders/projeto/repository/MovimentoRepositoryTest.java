package br.com.bycoders.projeto.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.bycoders.projeto.ApplicationBaseTest;
import br.com.bycoders.projeto.enums.TipoTransacao;
import br.com.bycoders.projeto.model.Loja;
import br.com.bycoders.projeto.model.Movimento;

/**
 * Testa o repositório de movimentos
 * @author bruno
 */
public class MovimentoRepositoryTest extends ApplicationBaseTest {
	
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
	
	@Test
	public void testInserirMovimento() {
		Loja loja = lojaRepository.getById(3L);
		assertNotNull(loja);
		Movimento mov = Movimento.builder()
				.cartao("9999****9999")
				.cpf("01152878174")
				.dataOcorrencia(LocalDate.now())
				.horaOcorrencia(LocalTime.now())
				.loja(loja)
				.tipoTransacao(TipoTransacao.DEBITO)
				.valor(new BigDecimal(1250)).build();
		movimentoRepository.save(mov);
		assertNotNull(mov.getId());
	}

}
