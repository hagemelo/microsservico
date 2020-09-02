package br.com.jhage.cadastrarcliente.service;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jhage.cadastrarcliente.exception.CadastrarClienteException;
import br.com.jhage.cadastrarcliente.repository.ClienteRepository;
import br.com.jhage.core.modelo.Cliente;

/**
 * 
 * @author Alexsander Melo
 * @since 02/12/2018
 * 
 */
@Service
public class CadastrarClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	private String value;
	private Cliente cliente;
	
	public void cadastrarCliente(ConsumerRecord<?, ?> valor) {
		
		this.value = new String((String) valor.value());
		System.out.println("msg:::" + value);
		
		try {
			this.jsonToobject();
			repository.save(this.cliente);
			
		} catch (CadastrarClienteException e) {
			e.printStackTrace();
		}
	}
	
	private void jsonToobject() throws CadastrarClienteException{
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			this.cliente = mapper.readValue(this.value, Cliente.class);
		} catch (IOException e) {
			
			throw new CadastrarClienteException(e.getMessage());
		}  
	}

}
