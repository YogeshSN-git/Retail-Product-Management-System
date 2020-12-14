package com.mvc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.mvc.model.UserData;

public interface PortalService {
	public ModelAndView authenticate(UserData userData, HttpServletRequest request);

	public ModelAndView displayProductList(HttpServletRequest request);

	public ModelAndView getProductById(int productid, HttpServletRequest request);

	public ModelAndView searchProduct(String productName, HttpServletRequest request);

	public ModelAndView displayCartDetails(HttpServletRequest request);

	public ModelAndView displayWishListDetails(HttpServletRequest request);

	public ModelAndView logout(HttpSession session);
}
