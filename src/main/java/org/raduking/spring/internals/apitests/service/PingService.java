package org.raduking.spring.internals.apitests.service;

import java.time.Duration;

import org.apiphany.logging.Slf4jLoggerAdapter;
import org.morphix.lang.retry.DelayStrategy;
import org.morphix.lang.retry.delay.ExponentialDelayStrategy;
import org.morphix.lang.thread.ReschedulingTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PingService.class);

	private final ReschedulingTask reschedulingTask;
	private final DelayStrategy failureDelayStrategy;

	private int pingCount = 0;
	private int consecutiveFails = 0;

	public PingService() {
		this.reschedulingTask = ReschedulingTask.builder()
				.task(this::ping)
				.nextDelay(this::getNextDelay)
				.logger(Slf4jLoggerAdapter.of(LOGGER))
				.build();
		this.failureDelayStrategy = ExponentialDelayStrategy.of(Duration.ofMillis(500), Duration.ofSeconds(10), 2.0);
		this.reschedulingTask.enable();
	}

	public void ping() {
		++pingCount;
		if (pingCount >= 5 && pingCount <= 10) {
			LOGGER.error("failed #{}", pingCount);
			++consecutiveFails;
		} else {
			consecutiveFails = 0;
			LOGGER.info("ping #{}", pingCount);
		}
	}

	public Duration getNextDelay() {
		if (consecutiveFails > 0) {
			long delay = failureDelayStrategy.delay(consecutiveFails - 1);
			return Duration.of(delay, failureDelayStrategy.chronoUnit());
		}
		return Duration.ofSeconds(1);
	}
}
