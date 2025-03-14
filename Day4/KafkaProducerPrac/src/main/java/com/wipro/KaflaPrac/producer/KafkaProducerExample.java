package com.wipro.KaflaPrac.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaProducerExample {

	public void runProducer() {
		Properties p = new Properties();
		p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG ,"localhost:9092");
		p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		
		
		KafkaProducer<String, String> producer = new KafkaProducer<>(p);
		for(int i=0; i<100;i++) {
			producer.send(new ProducerRecord<String, String>("test-topic", "key-"+i,"value-"+i));
		}
		producer.close();
	}

}
