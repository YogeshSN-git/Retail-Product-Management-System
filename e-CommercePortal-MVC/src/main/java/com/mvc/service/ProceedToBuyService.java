package com.mvc.service;

import java.text.ParseException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.requestAndResponse.CartRequest;

public interface ProceedToBuyService {

	ModelAndView getProductById(int productid, HttpSession session);

	ModelAndView addToWishList(int productid, HttpSession session, String referer);

	ModelAndView addToCart(@Valid CartRequest cartRequest, BindingResult br, HttpSession session) throws ParseException;

	ModelAndView proceedToBuy(CartRequest cartRequest, int productid, HttpSession session);

}
