package org.raduking.spring.internals.perf.controller;

import org.raduking.spring.internals.perf.PerformanceRunner;
import org.raduking.spring.internals.perf.model.PerformanceResults;
import org.raduking.spring.internals.perf.model.PerformanceSettings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = { PerformanceController.API_PERFORMANCE_RESOURCE })
public class PerformanceController {

	public static final String API_PERFORMANCE_RESOURCE = "/api/performance";

	private final PerformanceRunner performanceRunner;

	public PerformanceController(final PerformanceRunner performanceRunner) {
		this.performanceRunner = performanceRunner;
	}

	@PostMapping
	public ResponseEntity<PerformanceResults> runPerformance(@RequestBody final PerformanceSettings performanceSettings) {
		PerformanceResults results = performanceRunner.run(performanceSettings);
		return ResponseEntity.ok(results);
	}

}
