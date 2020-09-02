package br.com.jhage.command.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.jhage.command.exception.AgendaAtendimentoException;
import br.com.jhage.command.response.Response;

/**
 * 
 * @author Alexsander Melo
 * @since 01/12/2018
 *
 */
abstract class DefaultController {

	@ExceptionHandler(AgendaAtendimentoException.class)
	public ResponseEntity<Response> erroMensagens(AgendaAtendimentoException ex) {

		return ex.respostaErro();
	}
}
