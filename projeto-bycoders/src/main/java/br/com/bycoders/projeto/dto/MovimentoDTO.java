package br.com.bycoders.projeto.dto;

import java.time.format.DateTimeFormatter;

import br.com.bycoders.projeto.model.Movimento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimentoDTO {
	
	private String tipoTransacao;
	private String dataOcorrencia;
	private String sinal;
	private String valor;
	private String cpf;
	private String cartao;
	private String hora; 
	
	public static MovimentoDTO getFromMovimento(Movimento movimento) {
		MovimentoDTO movimentoDTO = new MovimentoDTO();
		movimentoDTO.setTipoTransacao(movimento.getTipoTransacao().getTransacao() + 
				" (" + movimento.getTipoTransacao().getNatureza() + ")");
		movimentoDTO.setDataOcorrencia(movimento.getDataOcorrencia().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		movimentoDTO.setSinal(movimento.getTipoTransacao().getSignal());
		movimentoDTO.setValor(movimento.getValor().toString());
		movimentoDTO.setCpf(movimento.getCpf());
		movimentoDTO.setCartao(movimento.getCartao());
		movimentoDTO.setHora(movimento.getHoraOcorrencia().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		return movimentoDTO;
	}

}
