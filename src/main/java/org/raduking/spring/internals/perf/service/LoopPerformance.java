package org.raduking.spring.internals.perf.service;

import org.springframework.stereotype.Service;
import org.stressium.AbstractPerformanceService;

@Service
public class LoopPerformance extends AbstractPerformanceService<Object> {

	@Override
	public void run(final Object customSettings) {
		for (int i = 0; i < 100_000; ++i) {
			// empty
		}
	}

	@Override
	public Class<Object> getCustomSettingsClass() {
		return Object.class;
	}

}
