package org.merchantservices.manager.exception;

public class OfferNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2702739359299206307L;

	public OfferNotFoundException() {
		super("Could not find offer!");
	}
}
