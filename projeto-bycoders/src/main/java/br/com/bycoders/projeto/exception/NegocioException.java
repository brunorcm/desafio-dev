package br.com.bycoders.projeto.exception;

public class NegocioException extends Exception {

	private static final long serialVersionUID = 8414158271329825134L;

	public NegocioException() {
		super();
	}

	public NegocioException(String message, Throwable cause) {
		super(message, cause);
	}

	public NegocioException(String message) {
		super(message);
	}

}
