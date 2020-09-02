package br.com.jhage.core;

import org.junit.Test;

import br.com.jhage.core.modelo.Cliente;

public class ClienteTest {

	@Test
	public void deveExtrairJson() throws Exception {
		
		Cliente cl = new Cliente("Test", "Tes", "qualquer lugar", "999999");
		System.out.println(cl.getNome());
		System.out.println(cl.getJsonValue());
		
	}
	
}
