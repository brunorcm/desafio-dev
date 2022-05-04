package br.com.bycoders.projeto.rest.resource;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.bycoders.projeto.dto.ExtratoDTO;
import br.com.bycoders.projeto.exception.NegocioException;
import br.com.bycoders.projeto.model.Loja;
import br.com.bycoders.projeto.repository.LojaRepository;
import br.com.bycoders.projeto.service.MovimentoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api")
@OpenAPIDefinition(
        info = @Info(
                title = "DESAFIO DEV :: LOJA API",
                description = " API para obter informações sobre lojas e seus movimentos",
                version = "1.0",
                contact = @Contact(name = "Bruno", email = "brunorcmorais@gmail.com")
                ))
public class LojaResource {
	
	@Autowired
	private LojaRepository lojaRepository;
	
	@Autowired
	private MovimentoService movimentoService;
	
	@Operation(
			  summary = "Retorna todas lojas cadastradas",
			  responses = {
					  	@ApiResponse(responseCode  = "200", description  = "Retorna todas as lojas"),			    
			  }
	)
	@GetMapping(value = "/lojas")
	public ResponseEntity<List<Loja>> listarLojas() {
		List<Loja> lojas = lojaRepository.findAll();
		return ResponseEntity.ok(lojas);
	}
	
	@Operation(
	  summary = "Retorna loja para o identificador informado",
	  responses = {
			  	@ApiResponse(responseCode  = "200", description  = "Retorna a loja correspondente"),	
			  	@ApiResponse(responseCode  = "400", description  = "Dados de entrada inválidos"),
			    @ApiResponse(responseCode  = "500", description  = "Foi gerado um erro interno"),
	  }
	)
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
	
	@Operation(
			  summary = "Retorna os movimentos para a loja cujo identificador for informado",
			  responses = {
					  	@ApiResponse(responseCode  = "200", description  = "Retorna os movimentos da loja"),
					  	@ApiResponse(responseCode  = "400", description  = "Dados de entrada inválidos"),
					    @ApiResponse(responseCode  = "500", description  = "Foi gerado um erro interno"),
			  }
	)
	@GetMapping(value = "/lojas/{idLoja}/movimentos")
	public ResponseEntity<ExtratoDTO> obterExtratoLoja(@PathVariable("idLoja") String idLoja) throws NegocioException {
		try {
			ExtratoDTO extrato = movimentoService.obterExtratoMovimentos(Long.parseLong(idLoja));
			return ResponseEntity.ok(extrato);
			
		} catch (NumberFormatException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} 
	}
	
	@Operation(
			  summary = "Recebe um arquivo de movimentos das lojas",
			  responses = {
					  	@ApiResponse(responseCode  = "200", description  = "Operação realizada com sucesso"),					  	
					    @ApiResponse(responseCode  = "500", description  = "Foi gerado um erro interno"),
			  }
	)
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/lojas/movimentos/upload", consumes = "multipart/form-data")	
	public ResponseEntity enviarMovimentos(@RequestParam("file")  MultipartFile file, Model model) throws IllegalStateException, IOException, NegocioException {
		File arqMovimentos = new File("/tmp/"+file.getOriginalFilename());
		file.transferTo(arqMovimentos);
		movimentoService.inserir(arqMovimentos);
		return ResponseEntity.ok().build();
	}
	

}
