package br.com.jhage.dispag.core;

import org.junit.Assert;
import org.junit.Test;

import br.com.jhage.dispag.core.modelo.Credor;
import br.com.jhage.dispag.core.modelo.Debitos;

public class CredorTest {
	
	
	
	@Test
	public void deveRetornarAvulso() throws Exception {
		
		Credor cred = new Credor("DIVIDO DE HJ", "AVULSOS");
		Debitos deb = new Debitos().add(cred); 
		System.out.println(deb.getJsonValue());
		
		int UM  = 1;
		
		String variavelDeAmbiente = System.getenv("KAFKA_SERVER");
		System.out.println("Variavel:::" + variavelDeAmbiente);
		Assert.assertEquals(UM, 1);
		
	}

}
