package lutz.algasensors.processor.api.controller;

import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lutz.algasensors.processor.api.model.TemperatureLogData;
import lutz.algasensors.processor.common.IdUtils;
import lutz.algasensors.processor.infrastructure.rabbitmq.RabbitMQConfig;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@Slf4j
@RestController
@RequestMapping("/api/sensors/{sensorId}/temperatures")
@RequiredArgsConstructor
public class ProcessorController {
	private final RabbitTemplate rabbitTemplate;

	@PostMapping(value = "/", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> save(@PathVariable TSID sensorId, @RequestBody String input) {
		if (input == null || input.isBlank()) {
			return ResponseEntity.badRequest().build();
		}

		double temperature;
		try {
			temperature = Double.parseDouble(input);
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}

		var tlo = new TemperatureLogData(
				IdUtils.generateTimeBasedUUID(),
				sensorId,
				OffsetDateTime.now(),
				temperature
		);

		log.info("Salvando TemperatureLog: {}", tlo);

		MessagePostProcessor messagePostProcessor = message -> {
			message.getMessageProperties().setHeader("sensorId", tlo.sensorId().toString());
			return message;
		};

		String routingKey = ""; // não funciona se não passar isso
		rabbitTemplate.convertAndSend(
				RabbitMQConfig.FANOUT_EXCHANGE_NAME,
				routingKey,
				tlo,
				messagePostProcessor
		                             );

		return ResponseEntity.ok().build();
	}
}
