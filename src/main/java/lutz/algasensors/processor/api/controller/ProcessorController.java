package lutz.algasensors.processor.api.controller;

import io.hypersistence.tsid.TSID;
import lombok.extern.slf4j.Slf4j;
import lutz.algasensors.processor.api.model.TemperatureLog;
import lutz.algasensors.processor.api.model.TemperatureLogId;
import lutz.algasensors.processor.common.IdUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/api/sensors/{sensorId}/temperatures")
@Slf4j
public class ProcessorController {

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

		var tlo = new TemperatureLog(
				new TemperatureLogId(IdUtils.generateTimeBasedUUID()),
				sensorId,
				OffsetDateTime.now(),
				temperature
		);

		log.info("Salvando TemperatureLog: {}", tlo);

		return ResponseEntity.ok().build();
	}
}
