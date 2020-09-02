package br.com.jhage.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.jhage.core.exception.NumberHelpException;
import br.com.jhage.core.helper.NumberHelp;
import br.com.jhage.core.modelo.ItenAtendimento;

/**
 * 
 * @author Alexsander Melo
 * @since 03/11/2018
 * 
 */
@RunWith(SpringRunner.class)
public class ItenAtendimentoTest {
	
	@TestConfiguration
    static class Config {
  
        @Bean
        public NumberHelp produceNumberHelp() {
            return new NumberHelp();
        }
        
        @Bean
        public ItenAtendimento produceItenAtendimento() {
            return new ItenAtendimento();
        }
    }
	
	
	@Autowired
	private NumberHelp numberHelp;
	
	@Autowired
	private ItenAtendimento itenAtendimento;
	
	@Test
	public void deve() throws NumberHelpException {
		
		System.out.println(numberHelp.parseDoubleToString(itenAtendimento.getValor()));
	}
}
