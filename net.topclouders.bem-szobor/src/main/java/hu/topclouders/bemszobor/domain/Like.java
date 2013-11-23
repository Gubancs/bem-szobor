package hu.topclouders.bemszobor.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Like extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4597919058940345684L;

	@ManyToOne
	private Visitor visitor;

	@ManyToOne
	private Protest demonstration;

	private Integer postIndex;

	@Temporal(TemporalType.TIMESTAMP)
	private Date likeDate;

	public Like() {
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public Protest getDemonstration() {
		return demonstration;
	}

	public void setDemonstration(Protest demonstration) {
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
