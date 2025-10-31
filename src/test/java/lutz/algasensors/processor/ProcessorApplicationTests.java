package lutz.algasensors.processor;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lutz.algasensors.processor.utils.IdUtils;

@SpringBootTest
class ProcessorApplicationTests {

	@Test
	void shouldGenerateUUIDv7() {
		var uuid1 = IdUtils.generateTimeBasedUUID();

		OffsetDateTime currentTime = OffsetDateTime
				.now()
				.truncatedTo(ChronoUnit.MINUTES);
		OffsetDateTime uuidTime = IdUtils
				.extractOffsetDateTime(uuid1)
				.truncatedTo(ChronoUnit.MINUTES);

		Assertions.assertEquals(uuidTime, currentTime);
	}
}
