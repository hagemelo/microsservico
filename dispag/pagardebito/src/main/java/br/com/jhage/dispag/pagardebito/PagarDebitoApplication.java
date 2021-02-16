package br.com.jhage.dispag.pagardebito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * 
 * @author Alexsander Melo
 * @since 15/02/2021
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
@EntityScan( basePackages = {"br.com.jhage.dispag.core.modelo"} )
public class PagarDebitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagarDebitoApplication.class, args).close();
	}

}
