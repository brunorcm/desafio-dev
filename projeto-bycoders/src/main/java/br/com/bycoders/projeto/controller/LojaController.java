package br.com.bycoders.projeto.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bycoders.projeto.exception.NegocioException;
import br.com.bycoders.projeto.service.MovimentoService;

@Controller
public class LojaController {
	
	@Autowired
	private MovimentoService movimentoService;
	
	@GetMapping("/cadastrar-movimentos")
	public String cadastrarMovimentos() {
		return "index";
	}
	
	@GetMapping(value = "/consultar-movimentos")
	public String consultarMovimentos(Model model, RedirectAttributes redirectAttributes) {
		try {
			model.addAttribute("extratos", movimentoService.obterExtratos());
			return "consultar-movimentos";
			
		} catch (NegocioException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/cadastrar-movimentos";
		}
	}
	
	@PostMapping(value = "/movimentos-upload", consumes = "multipart/form-data")	
	public String enviarMovimentos(@RequestParam("file")  MultipartFile file, Model model) throws IllegalStateException, IOException {
		try {
			File arqMovimentos = new File("/tmp/"+file.getOriginalFilename());
			file.transferTo(arqMovimentos);
			movimentoService.inserir(arqMovimentos);
			
		} catch (NegocioException e) {
			return "redirect:/index";
		}

		return "redirect:/consultar-movimentos";
	}


}
