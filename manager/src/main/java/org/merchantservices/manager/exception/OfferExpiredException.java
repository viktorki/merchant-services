package org.merchantservices.manager.exception;

public class OfferExpiredException extends RuntimeException {

	private static final long serialVersionUID = -8476922811204197685L;

	public OfferExpiredException() {
		super("Could not cancel expired offer!");
	}
}
