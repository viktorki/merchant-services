package org.merchantservices.manager.exception;

public class OfferCancelledException extends RuntimeException {

	private static final long serialVersionUID = 1359353570223206372L;

	public OfferCancelledException() {
		super("Offer has already been cancelled!");
	}
}
