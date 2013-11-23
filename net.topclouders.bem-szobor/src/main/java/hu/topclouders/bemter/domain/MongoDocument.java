package hu.topclouders.bemter.domain;

import org.springframework.data.annotation.Id;

public class MongoDocument {

	@Id
	private String id;

	public MongoDocument() {
	}

	public String getId() {
		return id;
	}
}
