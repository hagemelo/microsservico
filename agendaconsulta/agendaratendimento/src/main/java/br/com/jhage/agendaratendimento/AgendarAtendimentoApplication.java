package br.com.jhage.agendaratendimento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * 
 * @author Alexsander Melo
 * @since 02/12/2018
 *
 */
@SpringBootApplication
@EntityScan( basePackages = {"br.com.jhage.core.modelo"} )
public class AgendarAtendimentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendarAtendimentoApplication.class, args).close();
	}

}
