package br.com.jhage.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.jhage.command.exception.AgendaAtendimentoException;
import br.com.jhage.command.response.Response;
import br.com.jhage.core.exception.ConverterToStringException;
import br.com.jhage.core.modelo.Atendimento;

/**
 * 
 * @author Alexsander Melo
 * @since 02/12/2018
 *
 */

@Service
public class CommandService {
	
	private Atendimento atendimento;
	private static final String MENSAGEM = "Agenda ";
	private static final String SUCESSO = " efetuada com SUCESSO";
	private static final String CLIENTE_NOVO_TOPIC = "clientenovotopic";
	private static final String AGENDAR_ATENDIMENTO_TOPIC = "agendaratendimentotopic";
	
	@Autowired
	private KafkaTemplate<Integer, String> template;

	
	public Response agendarAtendimentoClienteNovo(Atendimento atendimento) throws AgendaAtendimentoException{
		
		this.atendimento = atendimento;
		cadastrarClienteNovo();
		agendarAtendimento();
		return criarMensagemSucessoAgenda();
	}
	
	private void cadastrarClienteNovo()throws AgendaAtendimentoException{
	
		try {
			
			this.template.send(CLIENTE_NOVO_TOPIC, atendimento.getCliente().getJsonValue());
		} catch (JsonProcessingException e) {
			
			throw new AgendaAtendimentoException(e);
		}
	}
	
	private void agendarAtendimento() throws AgendaAtendimentoException {
		try {
			
			this.template.send(AGENDAR_ATENDIMENTO_TOPIC, atendimento.getJsonValue());
		} catch (JsonProcessingException e) {

			throw new AgendaAtendimentoException(e);
		}
	}
	
	private Response criarMensagemSucessoAgenda() {

		StringBuffer buffer;
		try {
			
			buffer = new StringBuffer().append(MENSAGEM).append(this.atendimento.converterToString()).append(SUCESSO);
		} catch (ConverterToStringException e) {

			buffer = new StringBuffer().append(MENSAGEM).append(SUCESSO);
		}
		return new Response(buffer.toString());
	}

}
