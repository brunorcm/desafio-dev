package br.com.bycoders.projeto.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
	
	private static final int DEFAULT_BUFFER_SIZE = 8192;

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

	@Test
	public void testInsertMovimentos() throws FileNotFoundException, IOException, NegocioException {
		InputStream is = getClass().getClassLoader().getResourceAsStream("CNAB.txt");
		File arqMovimentos = new File("/tmp/file.txt");
		try (FileOutputStream outputStream = new FileOutputStream(arqMovimentos, false)) {
			int read;
			byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
			while ((read = is.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		}
		
		assertNotNull(arqMovimentos);		
		movimentoService.inserir(arqMovimentos);
		ExtratoDTO ext = movimentoService.obterExtratoMovimentos(6L);
		assertEquals(ext.getNomeLoja(), "MERCADO DA AVENIDA");
		assertEquals(ext.getMovimentosLoja().size(), 8);
	}

}
