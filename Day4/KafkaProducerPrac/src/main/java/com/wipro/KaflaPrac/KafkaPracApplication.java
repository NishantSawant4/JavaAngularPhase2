package com.wipro.KaflaPrac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wipro.KaflaPrac.producer.KafkaProducerExample;

@SpringBootApplication
public class KafkaPracApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaPracApplication.class, args);
		
		KafkaProducerExample producerExample = new KafkaProducerExample();
        producerExample.runProducer();

	}

}
