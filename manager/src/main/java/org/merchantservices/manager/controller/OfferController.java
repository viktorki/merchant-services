package org.merchantservices.manager.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.merchantservices.manager.dto.OfferDTO;
import org.merchantservices.manager.entity.Offer;
import org.merchantservices.manager.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "offers")
public class OfferController {

	@Autowired
	private OfferService offerService;

	@GetMapping(value = "{id}")
	public Offer getOffer(@PathVariable String id) {
		return offerService.getOffer(id);
	}

	@GetMapping
	public List<OfferDTO> getActiveOffers() {
		return offerService.getActiveOffers().stream().map(OfferDTO::new).collect(Collectors.toList());
	}

	@GetMapping("archived")
	public List<OfferDTO> getArchivedOffers() {
		return offerService.getArchivedOffers().stream().map(OfferDTO::new).collect(Collectors.toList());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void saveOffer(@Valid @RequestBody Offer offer) {
		offerService.saveOffer(offer);
	}

	@DeleteMapping(value = "{id}")
	public void cancelOffer(@PathVariable String id) {
		offerService.cancelOffer(id);
	}
}
