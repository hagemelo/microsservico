package br.com.jhage.command;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Alexsander Melo
 * @since 01/12/2018
 *
 */

@SpringBootApplication
public class CommandApplication implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
			
		System.in.read();
	}

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

}
