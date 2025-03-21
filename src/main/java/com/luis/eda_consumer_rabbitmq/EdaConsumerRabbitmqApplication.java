package com.luis.eda_consumer_rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRabbit
@EnableScheduling
public class EdaConsumerRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdaConsumerRabbitmqApplication.class, args);
	}

}
