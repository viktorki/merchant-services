package org.merchantservices.manager.dto;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.merchantservices.manager.entity.Offer;
import org.merchantservices.manager.util.Constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class OfferDTO {

	public OfferDTO(Offer offer) {
		id = offer.getId();
		title = offer.getTitle();
		content = offer.getContent();
		creationDate = offer.getCreationDate();
		expiryDate = offer.getExpiryDate();
		cancelDate = offer.getCancelDate();
	}

	private long id;

	@NotBlank
	private String title;

	@NotBlank
	private String content;

	@JsonFormat(pattern = Constants.DATE_FORMAT)
	private Date creationDate;

	@NotNull
	@Future
	@JsonFormat(pattern = Constants.DATE_FORMAT)
	private Date expiryDate;

	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = Constants.DATE_FORMAT)
	private Date cancelDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
