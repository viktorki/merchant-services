package org.merchantservices.manager.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.merchantservices.manager.dto.OfferDTO;
import org.merchantservices.manager.entity.Offer;
import org.merchantservices.manager.service.OfferService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(OfferController.class)
public class OfferControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private OfferService offerService;

	@Test
	public void testGetOffer() throws Exception {
		final Offer offer = Mockito.mock(Offer.class);
		final long id = 1001;
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
	public void testGetCancelledOffer() throws Exception {
		final Offer offer = Mockito.mock(Offer.class);
		final long id = 1002;
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

	@Test
	public void testGetActiveOffers() throws Exception {
		final Offer offer = Mockito.mock(Offer.class);
		final long id = 1003;
		final Integer idExpected = 1003;
		final String titleExpected = "Test Title 3";
		final String contentExpected = "Test content 3";

		Mockito.when(offer.getId()).thenReturn(id);
		Mockito.when(offer.getTitle()).thenReturn(titleExpected);
		Mockito.when(offer.getContent()).thenReturn(contentExpected);
		Mockito.when(offer.getCreationDate()).thenReturn(new Date());
		Mockito.when(offer.getExpiryDate()).thenReturn(new Date());
		Mockito.when(offerService.getActiveOffers()).thenReturn(Collections.singletonList(offer));

		mvc.perform(get("/offers")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(idExpected))).andExpect(jsonPath("$[0].title", is(titleExpected)))
				.andExpect(jsonPath("$[0].content", is(contentExpected)))
				.andExpect(jsonPath("$[0].creationDate", notNullValue()))
				.andExpect(jsonPath("$[0].expiryDate", notNullValue()));
	}

	@Test
	public void testGetArchivedOffers() throws Exception {
		final Offer offer = Mockito.mock(Offer.class);
		final long id = 1004;
		final Integer idExpected = 1004;
		final String titleExpected = "Test Title 4";
		final String contentExpected = "Test content 4";

		Mockito.when(offer.getId()).thenReturn(id);
		Mockito.when(offer.getTitle()).thenReturn(titleExpected);
		Mockito.when(offer.getContent()).thenReturn(contentExpected);
		Mockito.when(offer.getCreationDate()).thenReturn(new Date());
		Mockito.when(offer.getExpiryDate()).thenReturn(new Date());
		Mockito.when(offerService.getArchivedOffers()).thenReturn(Collections.singletonList(offer));

		mvc.perform(get("/offers/archived")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(idExpected))).andExpect(jsonPath("$[0].title", is(titleExpected)))
				.andExpect(jsonPath("$[0].content", is(contentExpected)))
				.andExpect(jsonPath("$[0].creationDate", notNullValue()))
				.andExpect(jsonPath("$[0].expiryDate", notNullValue()));
	}

	@Test
	public void testSaveOffer() throws Exception {
		final OfferDTO offerDTO = new OfferDTO();
		offerDTO.setTitle("Test Title 5");
		offerDTO.setContent("Test content 5");
		final Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 1);
		offerDTO.setExpiryDate(c.getTime());
		final String offerDTOString = new ObjectMapper().writeValueAsString(offerDTO);

		mvc.perform(post("/offers").contentType(MediaType.APPLICATION_JSON).content(offerDTOString))
				.andExpect(status().isCreated());

		Mockito.verify(offerService).saveOffer(Mockito.any(Offer.class));
	}

	@Test
	public void testCancelOffer() throws Exception {
		final long id = 1005;

		mvc.perform(delete("/offers/{id}", id)).andExpect(status().isOk());

		Mockito.verify(offerService).cancelOffer(id);
	}
}
