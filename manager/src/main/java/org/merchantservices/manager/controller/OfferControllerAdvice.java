package org.merchantservices.manager.controller;

import org.merchantservices.manager.exception.OfferCancelledException;
import org.merchantservices.manager.exception.OfferExpiredException;
import org.merchantservices.manager.exception.OfferNotFoundException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class OfferControllerAdvice {

	private static final String LOGREF = "error";

	@ExceptionHandler(InvalidFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public VndErrors handleOfferCancelledException(InvalidFormatException e) {
		return new VndErrors(LOGREF, "Invalid date format!");
	}

	@ExceptionHandler(OfferNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public VndErrors handleOfferNotFoundException(OfferNotFoundException e) {
		return new VndErrors(LOGREF, e.getMessage());
	}

	@ExceptionHandler(OfferCancelledException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public VndErrors handleOfferCancelledException(OfferCancelledException e) {
		return new VndErrors(LOGREF, e.getMessage());
	}

	@ExceptionHandler(OfferExpiredException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public VndErrors handleOfferCancelledException(OfferExpiredException e) {
		return new VndErrors(LOGREF, e.getMessage());
	}
}
