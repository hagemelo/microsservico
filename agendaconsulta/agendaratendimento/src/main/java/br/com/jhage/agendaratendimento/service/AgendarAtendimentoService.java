package br.com.jhage.agendaratendimento.service;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jhage.agendaratendimento.exception.AgendarAtendimentoException;
import br.com.jhage.agendaratendimento.repository.AtendimentoRepository;
import br.com.jhage.agendaratendimento.repository.ClienteRepository;
import br.com.jhage.agendaratendimento.repository.ProfissionalRepository;
import br.com.jhage.core.modelo.Atendimento;
import br.com.jhage.core.modelo.Cliente;
import br.com.jhage.core.modelo.Profissional;

/**
 * 
 * @author Alexsander Melo
 * @since 02/12/2018
 * 
 */
@Service
public class AgendarAtendimentoService {
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private AtendimentoRepository atendimentoRepository;
	
	private String value;
	private Atendimento atendimento;
	private Atendimento novoAtendimento;
	private Cliente cliente;
	private Profissional profissional;
	
	public void agendarAtendimento(ConsumerRecord<?, ?> valor) {
		
		this.value = new String((String) valor.value());
		System.out.println("Atend:::" + this.value);
		try {
			
			this.jsonToobject();
			this.carregarCliente();
			this.carregarProfissional();
			this.prepararAtendimento();
			this.atendimentoRepository.save(this.novoAtendimento);
		} catch (AgendarAtendimentoException e) {
			e.printStackTrace();
		}
	}
	
	private void carregarCliente() {
		
		this.cliente = clienteRepository.carregarCliente(atendimento.getCliente().getNome(), 
				atendimento.getCliente().getApelido());
	}
	
	private void carregarProfissional() {
		
		this.profissional = profissionalRepository.carregarProfissional(atendimento.getProfissional().getNome(),
				atendimento.getProfissional().getCro(), atendimento.getProfissional().getEspecialidade());
	}
	
	private void prepararAtendimento() {
		
		this.novoAtendimento = new Atendimento(atendimento.getProntuario(), 
				atendimento.getHora(), this.cliente, this.profissional, 
				atendimento.getPlano(), atendimento.getObs());
	}
	
	private void jsonToobject() throws AgendarAtendimentoException{
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			this.atendimento = mapper.readValue(this.value, Atendimento.class);
		} catch (IOException e) {
			
			throw new AgendarAtendimentoException(e.getMessage());
		}  
	}

}
