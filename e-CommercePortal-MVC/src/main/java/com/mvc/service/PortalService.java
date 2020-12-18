package com.mvc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.mvc.model.UserData;

public interface PortalService {
	/**
	 * 
	 * @param userData object of user details
	 * @param request  HttpServletRequest object
	 * @return Redirect to /home request to display Home page
	 */
	public ModelAndView authenticate(UserData userData, HttpServletRequest request);

	/**
	 * @param request HttpServletRequest object
	 * @return
	 */
	public ModelAndView displayProductList(HttpServletRequest request);

	/**
	 * @param productId Id to get ProductItem
	 * @param request   HttpServletRequest object
	 * @return
	 */
	public ModelAndView getProductById(int productId, HttpServletRequest request);

	/**
	 * @param productName Name to get ProductItem
	 * @param request     HttpServletRequest object
	 * @return
	 */
	public ModelAndView searchProduct(String productName, HttpServletRequest request);

	/**
	 * @param request HttpServletRequest object
	 * @return
	 */
	public ModelAndView displayCartDetails(HttpServletRequest request);

	/**
	 * @param request HttpServletRequest object
	 * @return
	 */
	public ModelAndView displayWishListDetails(HttpServletRequest request);

	/**
	 * @param session HttpSession object
	 * @return
	 */
	public ModelAndView logout(HttpSession session);
}
