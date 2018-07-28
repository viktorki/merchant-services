package org.merchantservices.manager.service;

import java.util.List;

import org.merchantservices.manager.entity.Offer;

public interface OfferService {

	Offer getOffer(long id);

	List<Offer> getActiveOffers();

	List<Offer> getArchivedOffers();

	Offer saveOffer(Offer offer);

	Offer cancelOffer(long id);
}
