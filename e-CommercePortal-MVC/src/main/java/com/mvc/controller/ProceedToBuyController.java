package com.mvc.controller;

import java.text.ParseException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.requestAndResponse.CartRequest;
import com.mvc.service.ProceedToBuyService;

@Controller
public class ProceedToBuyController {

	@Autowired
	ProceedToBuyService proceedToBuyService;

	@GetMapping("/addToCart")
	public ModelAndView addToCart(@RequestParam Integer productId,
			@ModelAttribute("cartRequest") CartRequest cartRequest, HttpSession session) {

		return proceedToBuyService.proceedToBuy(cartRequest, productId, session);

	}

	@PostMapping("/addProductToCart")
	public ModelAndView addToCart(@Valid @ModelAttribute("cartRequest") CartRequest cartRequest, BindingResult br,
			HttpSession session) throws ParseException {
		return proceedToBuyService.addToCart(cartRequest, br, session);
	}

	@GetMapping("/addToWishlist/{productid}")
	public ModelAndView addToWishlist(@PathVariable int productid, HttpSession session,
			@RequestHeader("Referer") String referer) {

		return proceedToBuyService.addToWishList(productid, session, referer);

	}

}
