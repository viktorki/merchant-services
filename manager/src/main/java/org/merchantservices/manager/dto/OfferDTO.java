package org.merchantservices.manager.dto;

import java.util.Date;

import org.merchantservices.manager.entity.Offer;

public class OfferDTO {

	public OfferDTO(Offer offer) {
		id = offer.getId();
		title = offer.getTitle();
		creationDate = offer.getCreationDate();
		expiryDate = offer.getExpiryDate();
		cancelDate = offer.getCancelDate();
	}

	private String id;

	private String title;

	private Date creationDate;

	private Date expiryDate;

	private Date cancelDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
}
