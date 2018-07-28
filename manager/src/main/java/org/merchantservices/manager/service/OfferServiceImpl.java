package org.merchantservices.manager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.merchantservices.manager.entity.Offer;
import org.merchantservices.manager.exception.OfferCancelledException;
import org.merchantservices.manager.exception.OfferExpiredException;
import org.merchantservices.manager.exception.OfferNotFoundException;
import org.merchantservices.manager.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferRepository offerRepository;

	@Override
	public Offer getOffer(long id) {
		Optional<Offer> offer = offerRepository.findById(id);

		if (offer.isPresent()) {
			return offer.get();
		}

		throw new OfferNotFoundException();
	}

	@Override
	public List<Offer> getActiveOffers() {
		return offerRepository.findByCancelDateIsNullAndExpiryDateGreaterThan(new Date());
	}

	@Override
	public List<Offer> getArchivedOffers() {
		return offerRepository.findByCancelDateIsNotNullOrExpiryDateLessThan(new Date());
	}

	@Override
	public Offer saveOffer(Offer offer) {
		offer.setCreationDate(new Date());

		return offerRepository.save(offer);
	}

	@Override
	public Offer cancelOffer(long id) {
		Offer offer = getOffer(id);

		if (offer.getCancelDate() != null) {
			throw new OfferCancelledException();
		}

		if (offer.getExpiryDate().before(new Date())) {
			throw new OfferExpiredException();
		}

		offer.setCancelDate(new Date());

		return offerRepository.save(offer);
	}
}
