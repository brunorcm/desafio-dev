package br.com.bycoders.projeto.enums;

public enum TipoTransacao {
	
	DEBITO(1, "Débito", "Entrada", "+"),
	BOLETO(2, "Boleto", "Saída", "-"),
	FINANCIAMENTO(3, "Financiamento", "Saída", "-"),
	CREDITO(4, "Crédito", "Entrada", "+"),
	RECEBIMENTO_EMPRESTIMO(5, "Recebimento Empréstimo", "Entrada", "+"),
	VENDAS(6, "Vendas", "Entrada", "+"),
	TED(7, "Recebimento TED", "Entrada", "+"),
	DOC(8, "Recebimento DOC", "Entrada", "+"),
	ALUGUEL(9, "Aluguel", "Saída", "-");
	
	private Integer codigo;
	private String transacao;
	private String natureza;
	private String signal;

	private TipoTransacao(Integer codigo, String transacao, String natureza, String signal) {
		this.codigo = codigo;
		this.transacao = transacao;
		this.natureza = natureza;
		this.signal = signal;
	}	
	
	public Integer getCodigo() {
		return codigo;
	}
	public String getTransacao() {
		return transacao;
	}
	public String getNatureza() {
		return natureza;
	}
	public String getSignal() {
		return signal;
	}
	
	public static TipoTransacao obterPorCodigo(Integer codigo) {
		for (TipoTransacao tipoTransacao: TipoTransacao.values()) {
			if (tipoTransacao.getCodigo().equals(codigo)) {
				return tipoTransacao;
			}
		}
		return null;
	}

}
