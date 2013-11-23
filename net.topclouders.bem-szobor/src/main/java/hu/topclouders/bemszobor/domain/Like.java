package hu.topclouders.bemszobor.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Like extends MongoDocument {

	private Visitor visitor;

	private Demonstration demonstration;

	private Integer postIndex;

	private Date likeDate;

	public Like() {
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public Demonstration getDemonstration() {
		return demonstration;
	}

	public void setDemonstration(Demonstration demonstration) {
		this.demonstration = demonstration;
	}

	public Integer getPostIndex() {
		return postIndex;
	}

	public void setPostIndex(Integer postIndex) {
		this.postIndex = postIndex;
	}

	public Date getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

}
