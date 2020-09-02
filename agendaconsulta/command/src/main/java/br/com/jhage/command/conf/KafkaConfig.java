package br.com.jhage.command.conf;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * 
 * @author Alexsander Melo
 * @since 02/12/2018
 *
 */
@Configuration
@EnableKafka
public class KafkaConfig {
	
	@Value("${kafka.boostrap.servers.config}")
	private String BOOTSTRAP_SERVERS_CONFIG;
	
	@Value("${kafka.retries.config}")
	private Integer  RETRIES_CONFIG;
	
	@Value("${kafka.batch.size.config}")
	private Integer BATCH_SIZE_CONFIG;
	
	@Value("${kafka.linger.ms.config}")
	private Integer LINGER_MS_CONFIG;
	
	@Value("${kafka.buffer.memory.config}")
	private Integer BUFFER_MEMORY_CONFIG;

	@Bean
    public KafkaTemplate<Integer, String> kafkaTemplate() {
		
        return new KafkaTemplate<Integer, String>(producerFactory());
    }
	
	@Bean
    public ProducerFactory<Integer, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
	
	@Bean
    public Map<String, Object> producerConfigs() {
		
		Map<String, Object> props = new HashMap<>();
	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_CONFIG);
	    props.put(ProducerConfig.RETRIES_CONFIG, RETRIES_CONFIG);
	    props.put(ProducerConfig.BATCH_SIZE_CONFIG, BATCH_SIZE_CONFIG);
	    props.put(ProducerConfig.LINGER_MS_CONFIG, LINGER_MS_CONFIG);
	    props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    return props;
	}
	
	
}
