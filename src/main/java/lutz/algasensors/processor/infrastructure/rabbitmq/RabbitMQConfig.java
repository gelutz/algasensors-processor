package lutz.algasensors.processor.infrastructure.rabbitmq;

import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.NonNull;

@Configuration
public class RabbitMQConfig {

	public static final String FANOUT_EXCHANGE_NAME = "processor.temperature-received.v1.e";

	@Bean
	public RabbitAdmin rabbitAdmin(@NonNull ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean
	public FanoutExchange exchange() {
		return ExchangeBuilder.fanoutExchange(FANOUT_EXCHANGE_NAME).build();
	}
}
