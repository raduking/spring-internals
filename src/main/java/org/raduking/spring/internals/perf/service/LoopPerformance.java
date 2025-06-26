package org.raduking.spring.internals.perf.service;

import org.springframework.stereotype.Service;
import org.stressium.AbstractPerformanceService;

@Service
public class LoopPerformance extends AbstractPerformanceService<LoopPerformance.Settings> {

	public static final int DEFAULT_ITERATIONS = 100_000;

	@Override
	public void run(final Settings customSettings) {
		for (int i = 0; i < customSettings.getIterations(); ++i) {
			// empty
		}
	}

	@Override
	public Class<Settings> getCustomSettingsClass() {
		return Settings.class;
	}

	static class Settings {

		private int iterations = DEFAULT_ITERATIONS;

		public int getIterations() {
			return iterations;
		}

		public void setIterations(final int iterations) {
			this.iterations = iterations;
		}

	}
}
