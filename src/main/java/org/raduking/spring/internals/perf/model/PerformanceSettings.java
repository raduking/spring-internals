package org.raduking.spring.internals.perf.model;

import java.util.HashMap;
import java.util.Map;

import org.apiphany.json.JsonBuilder;
import org.morphix.lang.function.Consumers;

public class PerformanceSettings {

	public static final int DEFAULT_THREAD_COUNT = 20;

	public static final int DEFAULT_LOOP_COUNT = 5;

	private String serviceName;

	private Integer threadCount = DEFAULT_THREAD_COUNT;

	private Integer loopCount = DEFAULT_LOOP_COUNT;

	private Map<String, Object> custom = new HashMap<>();

	@Override
	public String toString() {
		return JsonBuilder.toJson(this);
	}

	public Integer getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(final Integer threadsCount) {
		this.threadCount = threadsCount;
	}

	public Integer getLoopCount() {
		return loopCount;
	}

	public void setLoopCount(final Integer loopCount) {
		this.loopCount = loopCount;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(final String serviceName) {
		this.serviceName = serviceName;
	}

	public Map<String, Object> getCustom() {
		return custom;
	}

	public void setCustom(final Map<String, Object> custom) {
		this.custom = custom;
	}

	public <T> T getCustomProperties(final Class<T> cls) {
		Map<String, Object> properties = JsonBuilder.toMap(custom, Consumers.noConsumer());
		return JsonBuilder.fromMap(properties, cls, Consumers.noConsumer());
	}
}
