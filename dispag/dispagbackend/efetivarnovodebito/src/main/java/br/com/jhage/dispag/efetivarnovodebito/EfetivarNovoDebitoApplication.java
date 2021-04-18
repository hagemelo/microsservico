package br.com.jhage.dispag.efetivarnovodebito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * 
 * @author Alexsander Melo
 * @since 05/02/2021
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
@EntityScan( basePackages = {"br.com.jhage.dispag.core.modelo"} )
public class NovoDebitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NovoDebitoApplication.class, args).close();
	}

}
