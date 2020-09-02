package br.com.jhage.command.exception;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.jhage.command.response.Response;

/**
 * 
 * @author Alexsander Melo
 * @since 01/12/2018
 *
 */
public class AgendaAtendimentoException extends Exception {

		private static final long serialVersionUID = 1L;
		
		private static final String DEFAULT= "ERRRO_ACAO_ABRUPTA";

		public Logger inicializarLogger() {
			return LogManager.getLogger(AgendaAtendimentoException.class);
		}

		public AgendaAtendimentoException() {
			super(DEFAULT);
			this.inicializarLogger().error(DEFAULT);
		}
		
		public AgendaAtendimentoException(String message) {
			super(message);
			this.inicializarLogger().error(message);
		}

		
		public AgendaAtendimentoException(final Throwable cause) {

			super(DEFAULT);
			this.inicializarLogger().error(
					DEFAULT + " " + cause.getMessage());
		}

		public ResponseEntity<Response> respostaErro(){
			
			return new ResponseEntity<Response>(new Response(this.getMessage()),  HttpStatus.UNPROCESSABLE_ENTITY);
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
