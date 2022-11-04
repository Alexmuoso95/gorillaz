package com.gorillaz.core.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClientsKafkaProducer {
	private static final String TOPIC = "CLIENTS_TOPIC";

	public void produceMessage(String msg) {
		log.info("Building Kafka Producer");
		log.info("Configure");
		Properties prop = new Properties();
		prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		log.info("Create Producer");
		Producer<String, String> producer = new KafkaProducer<>(prop);

		log.info("Create Record");
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC, msg);

		log.info("Send Record");
		producer.send(record, new Callback() {
			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				// every time we send a message
				if (exception == null) {
					log.info(
							"Sucess record published topic : {} , key : {} , partition : {} , offset  : {} , timestamp : {}",
							metadata.topic(), record.key(), metadata.partition(), metadata.offset(),
							metadata.timestamp());
				} else {
					log.error("Something went wrong {}", exception.getMessage(), exception);
				}
			}
		});
		log.info("Flush");
		producer.flush();

		log.info("Close");
		producer.close();
	}
}
