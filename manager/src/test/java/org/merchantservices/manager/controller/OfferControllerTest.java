package org.merchantservices.manager.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.merchantservices.manager.entity.Offer;
import org.merchantservices.manager.service.OfferService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest({ OfferController.class })
public class OfferControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private OfferService offerService;

	@Test
	public void testGetOffer() throws Exception {
		final long id = 1001;
		final Offer offer = Mockito.mock(Offer.class);
		final Integer idExpected = 1001;
		final String titleExpected = "Test Title 1";
		final String contentExpected = "Test content 1";

		Mockito.when(offer.getId()).thenReturn(id);
		Mockito.when(offer.getTitle()).thenReturn(titleExpected);
		Mockito.when(offer.getContent()).thenReturn(contentExpected);
		Mockito.when(offer.getCreationDate()).thenReturn(new Date());
		Mockito.when(offer.getExpiryDate()).thenReturn(new Date());
		Mockito.when(offerService.getOffer(id)).thenReturn(offer);

		mvc.perform(get("/offers/{id}", id)).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(idExpected)))
				.andExpect(jsonPath("$.title", is(titleExpected))).andExpect(jsonPath("$.content", is(contentExpected)))
				.andExpect(jsonPath("$.creationDate", notNullValue()))
				.andExpect(jsonPath("$.expiryDate", notNullValue())).andExpect(jsonPath("$.cancelDate").doesNotExist());
	}

	@Test
	public void testGeCancelledtOffer() throws Exception {
		final long id = 1002L;
		final Offer offer = Mockito.mock(Offer.class);
		final Integer idExpected = 1002;
		final String titleExpected = "Test Title 2";
		final String contentExpected = "Test content 2";

		Mockito.when(offer.getId()).thenReturn(id);
		Mockito.when(offer.getTitle()).thenReturn(titleExpected);
		Mockito.when(offer.getContent()).thenReturn(contentExpected);
		Mockito.when(offer.getCreationDate()).thenReturn(new Date());
		Mockito.when(offer.getExpiryDate()).thenReturn(new Date());
		Mockito.when(offer.getCancelDate()).thenReturn(new Date());
		Mockito.when(offerService.getOffer(id)).thenReturn(offer);

		mvc.perform(get("/offers/{id}", id)).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(idExpected)))
				.andExpect(jsonPath("$.title", is(titleExpected))).andExpect(jsonPath("$.content", is(contentExpected)))
				.andExpect(jsonPath("$.creationDate", notNullValue()))
				.andExpect(jsonPath("$.expiryDate", notNullValue()))
				.andExpect(jsonPath("$.cancelDate", notNullValue()));
	}
}
