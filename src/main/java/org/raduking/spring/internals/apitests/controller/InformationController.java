package org.raduking.spring.internals.apitests.controller;

import org.apiphany.ApiClient;
import org.morphix.reflection.Methods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = { InformationController.API_INFO })
public class InformationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InformationController.class);

	public static final String API_INFO = ApiClient.API + "/info";

	public InformationController() {
		// empty
	}

	@GetMapping(path = "/name")
	public String getName() {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Called: {}", Methods.getCurrentMethodName(true));
		}
		return "Spring Internals";
	}

}
