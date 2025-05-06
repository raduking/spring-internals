package org.raduking.spring.internals.perf;

import java.util.List;
import java.util.Set;

import org.raduking.spring.internals.perf.config.PerformanceConfiguration;
import org.raduking.spring.internals.perf.model.PerformanceResults;
import org.raduking.spring.internals.perf.model.PerformanceSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * This class handles which performance service to run. By default it will run all performance services.
 *
 * @author Radu Sebastian LAZIN
 */
@Service
public class PerformanceRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceRunner.class);

	private final List<PerformanceService> performanceServices;

	private final PerformanceConfiguration performanceConfiguration;

	public PerformanceRunner(
			final PerformanceConfiguration performanceConfiguration,
			final List<PerformanceService> performanceServices) {
		LOGGER.info("Loading: {}", getClass().getSimpleName());
		this.performanceConfiguration = performanceConfiguration;
		this.performanceServices = performanceServices;
		this.performanceServices.stream()
				.filter(performanceConfiguration::isEnabled)
				.forEach(performanceService -> LOGGER.info("Enabled performance service: {}", performanceService.name()));
		LOGGER.info("Loaded: {}", getClass().getSimpleName());
	}

	public PerformanceResults run() {
		return run(new PerformanceSettings());
	}

	public PerformanceResults run(final PerformanceSettings performanceSettings) {
		PerformanceResults results = new PerformanceResults();
		Set<String> servicesToRun = performanceSettings.getServices().keySet();
		performanceServices.stream()
				.filter(performanceConfiguration::isEnabled)
				.filter(svc -> servicesToRun.contains(svc.getClass().getSimpleName()))
				.forEach(service -> results.addResult(service.run(performanceSettings)));
		return results;
	}
}
