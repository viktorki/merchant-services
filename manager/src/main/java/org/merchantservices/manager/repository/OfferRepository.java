package org.merchantservices.manager.repository;

import java.util.Date;
import java.util.List;

import org.merchantservices.manager.entity.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OfferRepository extends MongoRepository<Offer, String> {

	List<Offer> findByCancelDateIsNullAndExpiryDateGreaterThan(Date expiryDate);

	List<Offer> findByCancelDateIsNotNullOrExpiryDateLessThan(Date expiryDate);
}
