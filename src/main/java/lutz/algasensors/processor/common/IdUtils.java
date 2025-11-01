package lutz.algasensors.processor.common;

import com.fasterxml.uuid.Generators;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class IdUtils {
	public static java.util.UUID generateTimeBasedUUID() {
		return Generators.timeBasedEpochRandomGenerator().generate();
	}

	public static OffsetDateTime extractOffsetDateTime(UUID uuid) {
		if (uuid == null) {
			return null;
		}

		long timestamp = uuid.getMostSignificantBits() >>> 16;

		return OffsetDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
	}
}
