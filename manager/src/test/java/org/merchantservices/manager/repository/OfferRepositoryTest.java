package org.merchantservices.manager.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.merchantservices.manager.entity.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OfferRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private OfferRepository offerRepository;

	@Before
	public void before() {
		final Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 1);
		final Date date1 = c.getTime();
		c.add(Calendar.MONTH, 2);
		final Date date2 = c.getTime();
		final Offer offer1 = new Offer();
		offer1.setTitle("Test title 1");
		offer1.setContent("Test Content 1");
		offer1.setCreationDate(new Date());
		offer1.setExpiryDate(date1);
		final Offer offer2 = new Offer();
		offer2.setTitle("Test title 2");
		offer2.setContent("Test Content 2");
		offer2.setCreationDate(new Date());
		offer2.setExpiryDate(date2);
		final Offer offer3 = new Offer();
		offer3.setTitle("Test title 3");
		offer3.setContent("Test Content 3");
		offer3.setCreationDate(new Date());
		offer3.setExpiryDate(date2);
		offer3.setCancelDate(new Date());

		entityManager.persist(offer1);
		entityManager.persist(offer2);
		entityManager.persist(offer3);
	}

	@Test
	public void testFindByCancelDateIsNullAndExpiryDateGreaterThan() {
		final Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 2);
		final Date date = c.getTime();

		List<Offer> offers = offerRepository.findByCancelDateIsNullAndExpiryDateGreaterThan(date);

		Assert.assertEquals(1, offers.size());
		Assert.assertEquals("Test title 2", offers.get(0).getTitle());
	}

	@Test
	public void testFindByCancelDateIsNotNullOrExpiryDateLessThan() {
		final Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 2);
		final Date date = c.getTime();

		List<Offer> offers = offerRepository.findByCancelDateIsNotNullOrExpiryDateLessThan(date);

		Assert.assertEquals(2, offers.size());
		Assert.assertNotEquals("Test title 2", offers.get(0).getTitle());
		Assert.assertNotEquals("Test title 2", offers.get(1).getTitle());
	}
}
