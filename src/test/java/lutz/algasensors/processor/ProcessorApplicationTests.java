package lutz.algasensors.processor;

import lutz.algasensors.processor.utils.UUIDUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootTest
class ProcessorApplicationTests {

	@Test
	void shouldGenerateUUIDv7() {
		var uuid1 = UUIDUtils.generateTimeBasedUUID();

		OffsetDateTime currentTime = OffsetDateTime
				.now()
				.truncatedTo(ChronoUnit.MINUTES);
		OffsetDateTime uuidTime = UUIDUtils
				.extractOffsetDateTime(uuid1)
				.truncatedTo(ChronoUnit.MINUTES);


		Assertions.assertEquals(uuidTime, currentTime);
	}

}
