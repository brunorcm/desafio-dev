package br.com.bycoders.projeto.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.bycoders.projeto.ApplicationBaseTest;
import br.com.bycoders.projeto.dto.ExtratoDTO;
import br.com.bycoders.projeto.exception.NegocioException;
import br.com.bycoders.projeto.service.MovimentoService;

/**
 * Testa o serviço de movimentos
 * 
 * @author bruno
 */
public class MovimentoServiceTest extends ApplicationBaseTest {
	
	@Autowired
	private MovimentoService movimentoService;

	@Test
	public void testObterExtratosPorLoja() throws NegocioException {
		ExtratoDTO extrato = movimentoService.obterExtratoMovimentos(1L);
		assertNotNull(extrato);
		assertTrue(new BigDecimal(extrato.getSaldo()).compareTo(new BigDecimal(100)) == -1);
		assertEquals(extrato.getNomeLoja(), "LOJA1");
	}

	@Test
	public void testObterTodosExtratos() throws NegocioException {
		List<ExtratoDTO> extratos = movimentoService.obterExtratos();
		assertNotNull(extratos);
		BigDecimal saldo = BigDecimal.ZERO;
		for (ExtratoDTO ext : extratos) {
			saldo = saldo.add(new BigDecimal(ext.getSaldo()));
		}
		assertEquals(saldo, new BigDecimal(1538.50).setScale(2));
	}

}
