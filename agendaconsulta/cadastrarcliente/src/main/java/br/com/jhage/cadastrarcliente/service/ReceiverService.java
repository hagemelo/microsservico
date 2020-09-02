package br.com.jhage.cadastrarcliente.service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
	
/**
 * 
 * @author Alexsander Melo
 * @since 31/12/2018
 * 
 */
@Service
public class ReceiverService implements CommandLineRunner{
	
	private static final Logger logger = LogManager.getLogger(ReceiverService.class);


	private final CountDownLatch latch;
	
	@Autowired
	private CadastrarClienteService service;
	
	public ReceiverService(@Value("${kafka.number.receiver.threads}") Integer numberReceiverThreads) {
		
		latch = new CountDownLatch(numberReceiverThreads);
	}
	
	@KafkaListener(id = "${kafka.group.id.condif}", topics = "${kafka.topic}")
    public void listen(ConsumerRecord<?, ?> cr){
		
		try {
			logger.info("Consumer value::" + cr.toString());
			service.cadastrarCliente(cr);
		}catch (Exception e) {
			
			StringBuffer buffer  = new StringBuffer()
					.append("Erro ao receber valor::")
					.append(cr.toString())
					.append(" | Com Erro::")
					.append(e.getMessage());
			logger.error(buffer.toString());
			latch.countDown();
		}
    }
	
	@Override
	public void run(String... args) throws Exception {
		
		latch.await(60, TimeUnit.DAYS);
	}
}
