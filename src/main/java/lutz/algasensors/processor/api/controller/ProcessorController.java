package lutz.algasensors.processor.api.controller;

import io.hypersistence.tsid.TSID;
import lombok.extern.slf4j.Slf4j;
import lutz.algasensors.processor.api.model.TemperatureLogOutput;
import lutz.algasensors.processor.common.IdUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensors/{sensorId}/temperatures")
@Slf4j
public class ProcessorController {

	@PostMapping(value = "/", consumes = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<?> save(@PathVariable TSID sensorId, @RequestBody String input) {
		if (input == null || input.isBlank()) {
			return ResponseEntity.badRequest().build();
		}

		Double temperature;
		try {
			temperature = Double.valueOf(input);
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}

		var tlo = TemperatureLogOutput.builder()
		                              .id(IdUtils.generateTimeBasedUUID())
		                              .sensorId(sensorId)
		                              .value(temperature)
		                              .build();

		log.info("Salvando TemperatureLog: {}", tlo);

		return ResponseEntity.ok().build();
	}
}
