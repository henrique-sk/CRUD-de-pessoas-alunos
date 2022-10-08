package exception;

public class SistemaException extends Exception {

	private static final long serialVersionUID = 1L;

	public SistemaException(String mensagem) {
		super(mensagem);
	}
}