package br.com.jhage.dispag.novodebito;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.jhage.dispag.efetivarnovodebito.service.NovoDebitoConsumerService;

@Component
public class NovoDebitoKafkaConsumerTest extends NovoDebitoConsumerService{

	public NovoDebitoKafkaConsumerTest(@Value("${kafka.number.receiver.threads}") Integer numberReceiverThreads) {
		super(numberReceiverThreads);
	}
	
	
}
