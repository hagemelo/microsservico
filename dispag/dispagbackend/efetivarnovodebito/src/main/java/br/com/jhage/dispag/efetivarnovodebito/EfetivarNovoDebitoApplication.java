package br.com.jhage.dispag.efetivarnovodebito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

import br.com.jhage.dispag.core.conf.KafkaConfig;

/**
 * 
 * @author Alexsander Melo
 * @since 05/02/2021
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
@EntityScan( basePackages = {"br.com.jhage.dispag.core.modelo"} )
@Import(value= {KafkaConfig.class})
public class EfetivarNovoDebitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EfetivarNovoDebitoApplication.class, args).close();
	}

}
