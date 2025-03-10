package com.wipro.KaflaPrac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wipro.KaflaPrac.consumer.KafkaConsumerExample;

@SpringBootApplication
public class KafkaConsumerPracApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerPracApplication.class, args);
		KafkaConsumerExample consumerExample = new KafkaConsumerExample();
        consumerExample.runConsumer();
	}

}
