package com.mvc.service;

import java.text.ParseException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.requestAndResponse.CartRequest;

public interface ProceedToBuyService {

	/**
	 * Add product to wishlist with ProceedToBuyService
	 * 
	 * @param productid To get ProductItem
	 * @param session   To get Session attributes
	 * @param referer   To redirect to referer page
	 * @return {@code ProductPage}
	 */
	ModelAndView addToWishList(int productid, HttpSession session, String referer);

	/**
	 * Add product to Cart with ProceedToBuyService
	 * 
	 * @param cartRequest CartRequest attribute to get quantity, rating, zipcode
	 * @param br          Binding result for validation
	 * @param session     To get Session attributes
	 * @return {@code Cart} Page
	 * @throws ParseException Throws ParseException if date format doesn't match
	 */
	ModelAndView addToCart(@Valid CartRequest cartRequest, BindingResult br, HttpSession session) throws ParseException;

	/**
	 * 
	 * @param cartRequest CartRequest attribute to get quantity, rating, zipcode
	 * @param productid   To get ProductItem
	 * @param session     To get Session attributes
	 * @return {@code ProceedToBuy} Page to enter quantity, rating, zipcode
	 */
	ModelAndView proceedToBuy(CartRequest cartRequest, int productid, HttpSession session);

}
