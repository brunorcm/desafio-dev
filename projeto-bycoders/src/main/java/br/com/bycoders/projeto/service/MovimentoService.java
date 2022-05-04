package br.com.bycoders.projeto.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.bycoders.projeto.dto.ExtratoDTO;
import br.com.bycoders.projeto.enums.TipoTransacao;
import br.com.bycoders.projeto.exception.NegocioException;
import br.com.bycoders.projeto.model.Loja;
import br.com.bycoders.projeto.model.Movimento;
import br.com.bycoders.projeto.repository.LojaRepository;
import br.com.bycoders.projeto.repository.MovimentoRepository;
import br.com.bycoders.projeto.util.MovimentoUtil;

@Service
public class MovimentoService {
	
	@Autowired
	private LojaRepository lojaRepository;
	@Autowired
	private MovimentoRepository movimentoRepository;
	
	@Transactional
	public void inserir(MultipartFile file) throws NegocioException {
		 try {
			 File arqMovimentos = new File("/tmp/"+file.getOriginalFilename());
			 file.transferTo(arqMovimentos);
			 List<Object[]> movimentosObj = MovimentoUtil.parse(arqMovimentos);
			 for (Object[] obj: movimentosObj) {
				 Movimento mov = new Movimento();
				 mov.setTipoTransacao(TipoTransacao.obterPorCodigo((Integer) obj[0]));
				 mov.setDataOcorrencia((LocalDate) obj[1]);
				 mov.setValor((BigDecimal) obj[2]);
				 mov.setCpf((String) obj[3]);
				 mov.setCartao((String) obj[4]);
				 mov.setHoraOcorrencia((LocalTime) obj[5]);
				 
				 String nomeLoja = (String) obj[7];
				 Loja loja = lojaRepository.findByNome(nomeLoja);
				 if (loja == null) {
					 loja = new Loja();
					 String donoLoja = (String) obj[6];
					 loja.setNome(nomeLoja);
					 loja.setDono(donoLoja);
					 lojaRepository.save(loja);
				 }
				 mov.setLoja(loja);
				 movimentoRepository.save(mov);
			 }
		 } catch (IOException e) {				
			 throw new NegocioException(e.getMessage(), e);
		 }
	}
	
	public ExtratoDTO obterExtratoMovimentos(Long idLoja) throws NegocioException {
		Optional<Loja> optional = Optional.ofNullable(lojaRepository.findById(idLoja).orElseThrow(() -> new NegocioException("Loja não encontrada")));
		Loja loja = optional.get();		
		List<Movimento> movimentosLoja = movimentoRepository.findByLojaOrderByDataOcorrenciaDescHoraOcorrenciaDesc(loja);
		return ExtratoDTO.getExtratoDTO(loja, movimentosLoja);
	}
	
	public List<ExtratoDTO> obterExtratos() throws NegocioException {
		List<ExtratoDTO> extratos = new ArrayList<ExtratoDTO>();
		List<Loja> lojas = lojaRepository.findAll();
		if (lojas == null || lojas.isEmpty()) {
			throw new NegocioException("Não há lojas cadastradas");
		}
		for (Loja loja: lojas) {
			List<Movimento> movimentosLoja = movimentoRepository.findByLojaOrderByDataOcorrenciaDescHoraOcorrenciaDesc(loja);
			extratos.add(ExtratoDTO.getExtratoDTO(loja, movimentosLoja));
		}
		return extratos;
	}

}
