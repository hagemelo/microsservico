package br.com.jhage.command;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.jhage.command.response.Response;
import br.com.jhage.core.constante.Hora;
import br.com.jhage.core.constante.Plano;
import br.com.jhage.core.modelo.Atendimento;
import br.com.jhage.core.modelo.Cliente;
import br.com.jhage.core.modelo.Profissional;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes=CommandApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AtendimentoCommandControllerTest {

	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
//	private final Long LONG_ZERO = new Long(0);
	
	private final int RESPONSE_CODE = 200;
	
	@Test
	public void test() throws JsonProcessingException {
		
		Cliente cl = new Cliente("Test", "Tes", "qualquer lugar", "999999");
		Profissional prf = new Profissional("Dr Certo", "032032", "Cirurgia");
		Atendimento atend = new Atendimento("0987", Hora.H0830, cl, prf, Plano.AMIL_DENTAL, "");
		System.out.println(atend.getJsonValue());
	}
	
	
	public void deveRegistrarPedido() throws JsonProcessingException {
		
		String url = "/atendimentocommand/agendar/atendimento/cliente/novo";
		Cliente cl = new Cliente("Test", "Tes", "qualquer lugar", "999999");
		Profissional prf = new Profissional("Dr Certo", "032032", "Cirurgia");
		Atendimento atend = new Atendimento("0987", Hora.H0830, cl, prf, Plano.AMIL_DENTAL, "");
		System.out.println(atend.getJsonValue());
		ResponseEntity<Response> response = testRestTemplate.postForEntity(url, atend, Response.class);
		assertTrue(response.getStatusCodeValue() == RESPONSE_CODE);
	}
	
}
