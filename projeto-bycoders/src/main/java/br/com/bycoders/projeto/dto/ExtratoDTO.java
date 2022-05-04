package br.com.bycoders.projeto.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.bycoders.projeto.model.Loja;
import br.com.bycoders.projeto.model.Movimento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtratoDTO {
	
	private Long idLoja;
	private String nomeLoja;
	private String donoLoja;
	private List<MovimentoDTO> movimentosLoja = new ArrayList<MovimentoDTO>();
	private String saldo;
	
	public static ExtratoDTO getExtratoDTO(Loja loja, List<Movimento> movimentos) {
		BigDecimal calculoSaldo = BigDecimal.ZERO;
		ExtratoDTO dto = new ExtratoDTO();
		if (movimentos != null && !movimentos.isEmpty()) {
			dto.setIdLoja(loja.getId());
			dto.setNomeLoja(loja.getNome());
			dto.setDonoLoja(loja.getDono());
			
			for (Movimento mov: movimentos) {
				if (mov.getTipoTransacao().getSignal().equals("+")) {
					calculoSaldo = calculoSaldo.add(mov.getValor());
				} else {
					calculoSaldo = calculoSaldo.subtract(mov.getValor());
				}
				MovimentoDTO movDto = MovimentoDTO.getFromMovimento(mov);
				dto.getMovimentosLoja().add(movDto);
			}
			dto.setSaldo(calculoSaldo.toString());
		}
		return dto;
	}

}
