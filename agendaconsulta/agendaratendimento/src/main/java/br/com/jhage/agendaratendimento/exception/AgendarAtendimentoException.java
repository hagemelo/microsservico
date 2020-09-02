package br.com.jhage.agendaratendimento.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Alexsander Melo
 * @since 02/12/2018
 * 
 */
public class AgendarAtendimentoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT= "ERRRO_ACAO_ABRUPTA";
	
	public Logger inicializarLogger() {
		return LogManager.getLogger(AgendarAtendimentoException.class);
	}

	public AgendarAtendimentoException() {
		super(DEFAULT);
		this.inicializarLogger().error(DEFAULT);
	}
	
	public AgendarAtendimentoException(String message) {
		super(message);
		this.inicializarLogger().error(message);
	}
	
	@Override
	public String getMessage() {

		String message = super.getMessage();
		if (message == null || message.isEmpty()) {
			message = DEFAULT;
		}
		return message;
	}

}
