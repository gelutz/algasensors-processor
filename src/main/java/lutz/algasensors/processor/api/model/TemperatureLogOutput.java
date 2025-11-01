package lutz.algasensors.processor.api.model;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class TemperatureLogOutput {
	private UUID id;
	private TSID sensorId;
	@Builder.Default
	private OffsetDateTime registeredAt = OffsetDateTime.now();
	private Double value;
}
