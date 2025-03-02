package org.raduking.spring.internals.apitests.model;

import org.apiphany.json.JsonBuilder;

public class Customer {

	private String id;

	private String type;

	@Override
	public String toString() {
		return JsonBuilder.toJson(this);
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

}
