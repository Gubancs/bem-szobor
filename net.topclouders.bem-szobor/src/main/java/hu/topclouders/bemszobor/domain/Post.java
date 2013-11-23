package hu.topclouders.bemszobor.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Post implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8829671797921415807L;

	private String title;

	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
