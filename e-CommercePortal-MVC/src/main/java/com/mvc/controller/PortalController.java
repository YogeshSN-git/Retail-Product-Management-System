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

	/**
	 * View Login page
	 * 
	 * @return Login page
	 */
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("Login");
	}

	/**
	 * Call portalService authenticate method to validate user credentials
	 * 
	 * @param userData object of user details
	 * @param request  HttpServletRequest object
	 * @return Redirect to Home page if userData is valid
	 */
	@PostMapping("/authenticate")
	public ModelAndView authenticate(@ModelAttribute UserData userData, HttpServletRequest request) {
		return portalService.authenticate(userData, request);
	}

	/**
	 * Call portalService displayProductList method with HttpServletRequest argument
	 * 
	 * @param request HttpServletRequest object
	 * @return Home page displaying all products
	 */
	@GetMapping("/home")
	public ModelAndView home(@RequestParam int pageno, HttpServletRequest request) {
		return portalService.displayProductList(pageno, request);
	}

	/**
	 * Call portalService getProductById method with productId and
	 * HttpServletRequest arguments
	 * 
	 * @param productId Id to get ProductItem
	 * @param request   HttpServletRequest object
	 * @return ProductPage displaying product of given productId
	 */
	@GetMapping("/showProduct")
	public ModelAndView showProductPage(@RequestParam("productId") int productId, HttpServletRequest request) {
		return portalService.getProductById(productId, request);
	}

	/**
	 * Call portalService searchProduct method with productItem and
	 * HttpServletRequest arguments
	 * 
	 * @param productItem Id to get ProductItem
	 * @param request     HttpServletRequest object
	 * @return ProductPage displaying product of given productItem
	 */
	@PostMapping("/searchProduct")
	public ModelAndView searchProduct(@ModelAttribute ProductItem productItem, HttpServletRequest request) {
//		System.out.println("Hello" + productItem);
		return portalService.searchProduct(productItem.getProductName(), request);
	}

	/**
	 * Call portalService displayCartDetails method with HttpServletRequest argument
	 * 
	 * @param request HttpServletRequest object
	 * @return Cart page displaying cart products
	 */
	@RequestMapping(value = "/cart", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView Cart(HttpServletRequest request) {
		return portalService.displayCartDetails(request);
	}

	/**
	 * Call portalService displayWishListDetails method with HttpServletRequest
	 * argument
	 * 
	 * @param request HttpServletRequest object
	 * @return WishList page displaying wishList products
	 */
	@RequestMapping(value = "/wishlist", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView wishlist(HttpServletRequest request) {
		return portalService.displayWishListDetails(request);
	}

	/**
	 * Call portalService logout method with HttpSession argument
	 * 
	 * @param session HttpSession object
	 * @return Login page
	 */
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		return portalService.logout(session);

	}
}
