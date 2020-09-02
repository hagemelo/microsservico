package br.com.jhage.core.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author Alexsander Melo
 * @since 03/11/2018
 * 
 */
public class FormatDateHelperException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String DEFAULT= "ERRRO_ACAO_ABRUPTA";
	
	public Logger inicializarLogger() {
		return LogManager.getLogger(FormatDateHelperException.class);
	}

	public FormatDateHelperException() {
		super(DEFAULT);
		this.inicializarLogger().error(DEFAULT);
	}
	
	public FormatDateHelperException(String message) {
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
