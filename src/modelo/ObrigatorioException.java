package modelo;

public class ObrigatorioException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObrigatorioException() {
        super("Você precisa preencher todos os campos obrigatórios");
    }
}