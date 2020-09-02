package br.com.jhage.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jhage.command.exception.AgendaAtendimentoException;
import br.com.jhage.command.response.Response;
import br.com.jhage.command.service.CommandService;
import br.com.jhage.core.modelo.Atendimento;

/**
 * 
 * @author Alexsander Melo
 * @since 01/12/2018
 *
 */
@CrossOrigin(origins = {"http://localhost:3000"}, maxAge = 3000)
@RequestMapping("/atendimentocommand")
@RestController
public class AtendimentoCommandController extends DefaultController{
	
	@Autowired
	private CommandService service;
	
	@PostMapping(path= "/agendar/atendimento/cliente/novo")
	public @ResponseBody ResponseEntity<Response> agendarAtendimentoClienteNovo(@RequestBody Atendimento atendimento) throws AgendaAtendimentoException{

		return new ResponseEntity<Response>(service.agendarAtendimentoClienteNovo(atendimento), HttpStatus.OK);
	}

}
