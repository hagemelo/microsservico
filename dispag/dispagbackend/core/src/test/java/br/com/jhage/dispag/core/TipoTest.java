package br.com.jhage.dispag.core;



import org.junit.Assert;
import org.junit.Test;

import br.com.jhage.dispag.core.constante.Tipo;

public class TipoTest {

	@Test
	public void deveRetornarAvulso() throws Exception {
		
		String find  = "AVULSOS";
		
		Assert.assertEquals(Tipo.AVULSOS, Tipo.get(find));
		
	}
	
}
