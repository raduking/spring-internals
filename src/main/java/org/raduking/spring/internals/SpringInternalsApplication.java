package org.raduking.spring.internals;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringInternalsApplication {

	public static void main(final String[] args) {
		System.setProperty("jdk.httpclient.HttpClient.log", "all");

		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();

		SpringApplication.run(SpringInternalsApplication.class, args);
	}

}
