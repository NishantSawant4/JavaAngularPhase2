package com.wipro.KaflaPrac.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Consumer;

public class KafkaConsumerExample {

	public void runConsumer() {
		Properties p = new Properties();
		p.put("bootstrap.servers" ,"localhost:9092");
		p.put("group.id","test-group");
		p.put("key.deserializer", StringDeserializer.class.getName());
		p.put("value.deserializer", StringDeserializer.class.getName());
		
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(p);
		consumer.subscribe(Arrays.asList("test-topic"));
		while(true) {
			consumer.poll(1000).forEach(record->{System.out.println("Consumed Message "+record.value());});
		}
	}

}
