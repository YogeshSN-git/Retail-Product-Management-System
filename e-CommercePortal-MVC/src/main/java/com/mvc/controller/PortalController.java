package com.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.model.ProductItem;
import com.mvc.model.UserData;
import com.mvc.service.PortalService;
import com.mvc.service.ProceedToBuyService;

@RestController
public class PortalController {
	@Autowired
	PortalService portalService;
	@Autowired
	ProceedToBuyService proceedToBuyService;

	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("Login");
	}

	@PostMapping("/authenticate")
	public ModelAndView authenticate(@ModelAttribute UserData userData, HttpServletRequest request) {
		return portalService.authenticate(userData, request);
	}

	@GetMapping("/home")
	public ModelAndView home(HttpServletRequest request) {
		return portalService.displayProductList(request);
	}

	@GetMapping("/showProduct")
	public ModelAndView showProductPage(@RequestParam("productId") int productId, HttpServletRequest request) {
		return portalService.getProductById(productId, request);
	}

	@PostMapping("/searchProduct")
	public ModelAndView searchProduct(@ModelAttribute ProductItem productItem, HttpServletRequest request) {
//		System.out.println("Hello" + productItem);
		return portalService.searchProduct(productItem.getProductName(), request);
	}

	@RequestMapping(value = "/cart", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView Cart(HttpServletRequest request) {
		return portalService.displayCartDetails(request);
	}

	@RequestMapping(value = "/wishlist", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView wishlist(HttpServletRequest request) {
		return portalService.displayWishListDetails(request);
	}

	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		return portalService.logout(session);

	}
}
