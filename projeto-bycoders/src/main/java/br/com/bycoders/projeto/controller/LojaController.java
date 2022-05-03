package br.com.bycoders.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bycoders.projeto.dto.ExtratoDTO;
import br.com.bycoders.projeto.exception.NegocioException;
import br.com.bycoders.projeto.model.Loja;
import br.com.bycoders.projeto.repository.LojaRepository;
import br.com.bycoders.projeto.service.MovimentoService;

@RestController
@RequestMapping("/api")
public class LojaController {
	
	@Autowired
	private LojaRepository lojaRepository;
	
	@Autowired
	private MovimentoService movimentoService;
	
	@GetMapping(value = "/lojas")
	public ResponseEntity<List<Loja>> listarLojas() {
		List<Loja> lojas = lojaRepository.findAll();
		return ResponseEntity.ok(lojas);
	}
	
	@PostMapping(value = "/lojas/movimentos", consumes = "multipart/form-data")	
	public String enviar(@RequestParam("file")  MultipartFile file) {
		
		try {
			movimentoService.inserir(file);
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}

		return "movimentos";
	}
	
	@GetMapping(value = "/lojas/{idLoja}/movimentos")
	public ResponseEntity<ExtratoDTO> obterExtratoLoja(@PathVariable("idLoja") String idLoja) {
		try {
			ExtratoDTO extrato = movimentoService.obterExtratoMovimentos(Long.parseLong(idLoja));
			return ResponseEntity.ok(extrato);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ResponseEntity.noContent().build();
		} catch (NegocioException e) {
			e.printStackTrace();
			return ResponseEntity.noContent().build();
		}
	}

}
