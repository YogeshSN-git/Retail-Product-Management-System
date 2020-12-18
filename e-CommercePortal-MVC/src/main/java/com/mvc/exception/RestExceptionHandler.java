package com.mvc.exception;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.persistence.NoResultException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.requestAndResponse.MessageResponse;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

	@ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
	@ExceptionHandler(SocketTimeoutException.class)
	public ModelAndView handleSocketTimeoutException(SocketTimeoutException ex) {
		log.error(ex.getMessage());

		ModelAndView model = new ModelAndView("Login");
		model.addObject("errormsg", "Service down.Please,try again later...");
		return model;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(FeignException.InternalServerError.class)
	public ModelAndView handleFeignInternalServerError(FeignException ex) {
		log.error("Internal Server Error");

		ModelAndView model = new ModelAndView("Login");
		model.addObject("errormsg", "Service down.Please,try again later...");
		return model;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FeignException.BadRequest.class)
	public ModelAndView handleFeignBadRequestExceptions(FeignException ex) {

		String[] split = ex.getMessage().split("message")[1].split(",");

		String errorMessage = split[0].substring(3, split[0].length() - 1);

		if (errorMessage.contains("Authorisation required\"}")
				|| errorMessage.contains("Unauthorized request. Login again...")) {
			ModelAndView model = new ModelAndView("Login");
			model.addObject("errormsg", "Session Expired...");
			return model;
		}

		ModelAndView feignError = new ModelAndView("feignError");
		feignError.addObject("errorMessage", errorMessage);

		log.error("Feign Bad Request Exception");
		return feignError;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FeignException.UnprocessableEntity.class)
	public ModelAndView handleNonUniqueResultException(FeignException ex) {

		String[] split = ex.getMessage().split("message")[1].split(",");

		String errorMessage = split[0].substring(3, split[0].length() - 3);

		if (errorMessage.contains("unauthorized user")) {
			ModelAndView model = new ModelAndView("Login");
			model.addObject("errormsg", "Session Expired...");
			return model;
		}

		ModelAndView feignError = new ModelAndView("Home");
		feignError.addObject("error", errorMessage);

		log.error("Feign Non unique value Exception");
		return feignError;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FeignException.NotFound.class)
	public ModelAndView handleFeignNotFoundExceptions(FeignException ex) {

		String[] split = ex.getMessage().split("\"message\"")[1].split(",");
		String errorMessage = split[0].substring(2, split[0].length() - 1);

		if (errorMessage.contains("unauthorized user") || errorMessage.contains("No message available")) {
			ModelAndView model = new ModelAndView("Login");
			model.addObject("errormsg", "Session Expired...");
			return model;
		}

		ModelAndView feignError = new ModelAndView("Home");
		feignError.addObject("error", errorMessage);

		log.error("Feign Not Found Exception");

		return feignError;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConnectException.class)
	public ModelAndView handleConnectExceptions(ConnectException ex) {

		log.error("Client service down");
		ModelAndView model = new ModelAndView("Login");
		model.addObject("errormsg", "Service down.Please,try again later...");
		return model;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NoResultException.class)
	public ModelAndView handleNoResultException(NoResultException ex) {

		log.error(ex.getMessage());
		ModelAndView model = new ModelAndView("Cart");
		model.addObject("msg", ex.getMessage());
		return model;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<?> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {

		log.error("Required Bearer token");
		return ResponseEntity.badRequest().body(new MessageResponse("Required Bearer token", HttpStatus.UNAUTHORIZED));
	}

}
