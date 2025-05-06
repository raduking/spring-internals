package org.raduking.spring.internals.perf.config;

import java.util.ArrayList;
import java.util.List;

import org.raduking.spring.internals.perf.PerformanceService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration for performance tests.
 *
 * @author Radu Sebastian LAZIN
 */
@Component
@ConfigurationProperties(prefix = "performance")
public class PerformanceConfiguration {

	private List<String> enabled = new ArrayList<>();

	public List<String> getEnabled() {
		return enabled;
	}

	public void setEnabled(final List<String> enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled(final PerformanceService performanceService) {
		return enabled.contains(performanceService.name());
	}

	public boolean isDisabled(final PerformanceService performanceService) {
		return !isEnabled(performanceService);
	}

}
