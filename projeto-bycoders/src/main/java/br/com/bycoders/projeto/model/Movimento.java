package br.com.bycoders.projeto.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.bycoders.projeto.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private Long id;
	
	@ManyToOne(optional = false)
	private Loja loja;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private TipoTransacao tipoTransacao;
	
	@Column(nullable = false)
	private LocalDate dataOcorrencia;
	
	@Column(nullable = false)
	private BigDecimal valor;
	
	@Column(nullable = false)
	private String cpf;
	
	@Column(nullable = false)
	private String cartao;
	
	@Column(nullable = false)
	private LocalTime horaOcorrencia;
	
	

}
