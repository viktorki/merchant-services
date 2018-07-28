package org.merchantservices.manager.repository;

import java.util.Date;
import java.util.List;

import org.merchantservices.manager.entity.Offer;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends CrudRepository<Offer, Long> {

	List<Offer> findByCancelDateIsNullAndExpiryDateGreaterThan(Date expiryDate);

	List<Offer> findByCancelDateIsNotNullOrExpiryDateLessThan(Date expiryDate);
}
