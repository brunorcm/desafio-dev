package br.com.bycoders.projeto.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import br.com.bycoders.projeto.service.MovimentoService;

@RestController
@RequestMapping("/api")
public class MovimentoController {
	
	@Autowired
	private MovimentoService movimentoService;
	
	@PostMapping(value = "/movimentos", consumes = "multipart/form-data") 
	public String enviar(@RequestParam("file")  MultipartFile file,
			RedirectAttributes redirectAttributes) {
		
		try {
			movimentoService.inserir(file);
			redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
			
		} catch (NegocioException e) {
			e.printStackTrace();
		}

		return "redirect:/";
	}
	
	@GetMapping(value = "/movimentos/{idLoja}")
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
