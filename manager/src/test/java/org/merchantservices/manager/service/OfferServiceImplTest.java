package org.merchantservices.manager.service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.merchantservices.manager.entity.Offer;
import org.merchantservices.manager.exception.OfferCancelledException;
import org.merchantservices.manager.exception.OfferExpiredException;
import org.merchantservices.manager.exception.OfferNotFoundException;
import org.merchantservices.manager.repository.OfferRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class OfferServiceImplTest {

	@TestConfiguration
	static class OfferServiceImplTestContextConfiguration {

		@Bean
		public OfferService offerService() {
			return new OfferServiceImpl();
		}
	}

	@Autowired
	private OfferService offerService;

	@MockBean
	private OfferRepository offerRepository;

	@Test
	public void testGetOffer() {
		final String id = "1001";
		final Offer offerExpected = Mockito.mock(Offer.class);

		Mockito.when(offerRepository.findById(id)).thenReturn(Optional.of(offerExpected));

		Offer offer = offerService.getOffer(id);

		Assert.assertEquals(offerExpected, offer);
	}

	@Test(expected = OfferNotFoundException.class)
	public void testGetNotExistingOffer() {
		final String id = "1002";

		Mockito.when(offerRepository.findById(id)).thenReturn(Optional.empty());

		offerService.getOffer(id);
	}

	@Test
	public void testGetActiveOffers() {
		final List<Offer> offersExpected = Collections.singletonList(Mockito.mock(Offer.class));

		Mockito.when(offerRepository.findByCancelDateIsNullAndExpiryDateGreaterThan(Mockito.any(Date.class)))
				.thenReturn(offersExpected);

		List<Offer> offers = offerService.getActiveOffers();

		Assert.assertEquals(offersExpected, offers);
	}

	@Test
	public void testGetArchivedOffers() {
		final List<Offer> offersExpected = Collections.singletonList(Mockito.mock(Offer.class));

		Mockito.when(offerRepository.findByCancelDateIsNotNullOrExpiryDateLessThan(Mockito.any(Date.class)))
				.thenReturn(offersExpected);

		List<Offer> offers = offerService.getArchivedOffers();

		Assert.assertEquals(offersExpected, offers);
	}

	@Test
	public void testSaveOffer() {
		final Offer offer = Mockito.mock(Offer.class);
		final Offer savedOfferExpected = Mockito.mock(Offer.class);

		Mockito.when(offerRepository.save(offer)).thenReturn(savedOfferExpected);

		Offer savedOffer = offerService.saveOffer(offer);

		Mockito.verify(offer).setCreationDate(Mockito.any(Date.class));
		Assert.assertEquals(savedOfferExpected, savedOffer);
	}

	@Test
	public void testCancelOffer() {
		final String id = "1003";
		final Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 1);
		final Date expiryDate = c.getTime();
		final Offer offer = Mockito.mock(Offer.class);
		final Offer cancelledOfferExpected = Mockito.mock(Offer.class);

		Mockito.when(offer.getExpiryDate()).thenReturn(expiryDate);
		Mockito.when(offerRepository.findById(id)).thenReturn(Optional.of(offer));
		Mockito.when(offerRepository.save(offer)).thenReturn(cancelledOfferExpected);

		Offer cancelledOffer = offerService.cancelOffer(id);

		Mockito.verify(offer).setCancelDate(Mockito.any(Date.class));
		Assert.assertEquals(cancelledOfferExpected, cancelledOffer);
	}

	@Test(expected = OfferCancelledException.class)
	public void testCancelAlreadyCancelledOffer() {
		final String id = "1004";
		final Offer offer = Mockito.mock(Offer.class);

		Mockito.when(offer.getCancelDate()).thenReturn(new Date());
		Mockito.when(offerRepository.findById(id)).thenReturn(Optional.of(offer));

		offerService.cancelOffer(id);
	}

	@Test(expected = OfferExpiredException.class)
	public void testCancelExpiredOffer() {
		final String id = "1005";
		final Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		final Date expiryDate = c.getTime();
		final Offer offer = Mockito.mock(Offer.class);

		Mockito.when(offer.getExpiryDate()).thenReturn(expiryDate);
		Mockito.when(offerRepository.findById(id)).thenReturn(Optional.of(offer));

		offerService.cancelOffer(id);
	}
}
