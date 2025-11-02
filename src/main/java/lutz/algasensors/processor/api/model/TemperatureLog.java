package lutz.algasensors.processor.api.model;

import io.hypersistence.tsid.TSID;

import java.time.OffsetDateTime;

public record TemperatureLog(
		TemperatureLogId id,
		TSID sensorId,
		OffsetDateTime registeredAt,
		Double value) {
}
