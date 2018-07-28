package org.merchantservices.manager.dto;

import java.util.Date;

import org.merchantservices.manager.entity.Offer;

public class OfferDTO {

	public OfferDTO(Offer offer) {
		id = offer.getId();
		title = offer.getTitle();
		expiryDate = offer.getExpiryDate();
	}

	private String id;

	private String title;

	private Date expiryDate;

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

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}
