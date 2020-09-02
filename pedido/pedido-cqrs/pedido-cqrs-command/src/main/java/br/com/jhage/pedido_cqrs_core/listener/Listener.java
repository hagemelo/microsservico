package br.com.jhage.pedido_cqrs_core.listener;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;

public class Listener {

	private final CountDownLatch latch1 = new CountDownLatch(1);

    @KafkaListener(id = "test", topics = "test")
    public void listen1(String foo) {
        
    	
    	
    	this.latch1.countDown();
    }
}
