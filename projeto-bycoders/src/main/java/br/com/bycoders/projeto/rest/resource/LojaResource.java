package br.com.bycoders.projeto.rest.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bycoders.projeto.dto.ExtratoDTO;
import br.com.bycoders.projeto.exception.NegocioException;
import br.com.bycoders.projeto.model.Loja;
import br.com.bycoders.projeto.repository.LojaRepository;
import br.com.bycoders.projeto.service.MovimentoService;

@RestController
@RequestMapping("/api")
public class LojaResource {
	
	@Autowired
	private LojaRepository lojaRepository;
	
	@Autowired
	private MovimentoService movimentoService;
	
	@GetMapping(value = "/lojas")
	public ResponseEntity<List<Loja>> listarLojas() {
		List<Loja> lojas = lojaRepository.findAll();
		return ResponseEntity.ok(lojas);
	}
	
	@GetMapping(value = "/lojas/{idLoja}")
	public ResponseEntity<Loja> recuperarLoja(@PathVariable("idLoja") String idLoja) throws NegocioException {
		try {
			Optional<Loja> loja = lojaRepository.findById(Long.parseLong(idLoja));
			if (loja.isPresent()) {
				return ResponseEntity.ok(loja.get());
			} else {
				throw new NegocioException("Loja não encontrada");
			}
		} catch (NumberFormatException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/lojas/{idLoja}/movimentos")
	public ResponseEntity<ExtratoDTO> obterExtratoLoja(@PathVariable("idLoja") String idLoja) throws NegocioException {
		try {
			ExtratoDTO extrato = movimentoService.obterExtratoMovimentos(Long.parseLong(idLoja));
			return ResponseEntity.ok(extrato);
			
		} catch (NumberFormatException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
	}

}
