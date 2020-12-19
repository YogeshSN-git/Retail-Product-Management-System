package com.mvc.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.feign.AuthFeign;
import com.mvc.feign.ProceedToBuyFeign;
import com.mvc.feign.ProductFeign;
import com.mvc.feign.VendorFeign;
import com.mvc.model.Cart;
import com.mvc.model.ProductItem;
import com.mvc.model.UserData;
import com.mvc.model.Wishlist;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PortalServiceImpl implements PortalService {

	@Autowired
	AuthFeign authClient;
	@Autowired
	ProductFeign productClient;
	@Autowired
	VendorFeign vendorClient;

	@Autowired
	ProceedToBuyFeign proceedToBuyClient;

	@Autowired
	ProceedToBuyService proceedToBuyService;

	public ModelAndView authenticate(UserData userData, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		UserData userToken = null;
		try {
			userToken = authClient.login(userData);
		} catch (Exception e) {
			ModelAndView model = new ModelAndView("Login");
			model.addObject("errormsg", "Incorrect Username or Password");
			return model;
		}
		request.getSession().setAttribute("token", "Bearer " + userToken.getAuthToken());
		request.getSession().setAttribute("name", userToken.getUserid());
		modelAndView.setViewName("redirect:home?pageno=1");
		return modelAndView;
	}

	@Override
	public ModelAndView displayProductList(int pageno, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("token");
		List<ProductItem> productList = productClient.getAll(pageno, token);

		ModelAndView model = new ModelAndView("Home");
		model.addObject("productList", productList);
		return model;
	}

	@Override
	public ModelAndView searchProduct(String productName, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("token");

		if (!productName.matches("[0-9]+")) {
			log.info("searchProduct by name");

			ProductItem productItem = productClient.searchProductByName(token, productName);
//			return getProductById(productItem.getProductId(), request);

			ModelAndView modelView = new ModelAndView("productpage");
			modelView.addObject("productItem", productItem);
			modelView.addObject("stock", vendorClient.getProductStock(productItem.getProductId(), token));
			return modelView;
		} else {

			return getProductById(Integer.parseInt(productName), request);
		}
	}

	@Override
	public ModelAndView getProductById(int productid, HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("token");

		log.info("searchProduct By Id");
		ProductItem productItem = productClient.searchProductById(token, productid);

		ModelAndView modelView = new ModelAndView("productpage");
		modelView.addObject("productItem", productItem);
		modelView.addObject("stock", vendorClient.getProductStock(productid, token));
		return modelView;
	}

	@Override
	public ModelAndView displayCartDetails(HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("token");
		String customerid = (String) request.getSession().getAttribute("name");

		Optional<Cart> cart = proceedToBuyClient.viewCart(token, customerid);

		if (cart.isEmpty()) {
			throw new NoResultException("Your cart is empty");
		}

		log.info("show cart details");
		Cart cartList = cart.get();

		List<ProductItem> productList = cartList.getProductList();

		ModelAndView model = new ModelAndView("Cart");

		model.addObject("msg", "Your cart List");

		model.addObject("cartList", cartList);
		model.addObject("productList", productList);
		return model;

	}

	@Override
	public ModelAndView displayWishListDetails(HttpServletRequest request) {
		String token = (String) request.getSession().getAttribute("token");
		String customerid = (String) request.getSession().getAttribute("name");

		Optional<Wishlist> wishlist1 = proceedToBuyClient.viewWishList(token, customerid);

		if (wishlist1.isEmpty()) {
			throw new NoResultException("Your wishlist is empty");
		}

		Wishlist wishList = wishlist1.get();

		List<ProductItem> productList = wishList.getProductList();

		ModelAndView model = new ModelAndView("WishList");
		model.addObject("wishList", wishList);
		model.addObject("productList", productList);
		return model;
	}

	@Override
	public ModelAndView logout(HttpSession session) {
		session.setAttribute("token", null);
		session.setAttribute("name", null);
		session.invalidate();
		ModelAndView modelAndView = new ModelAndView("Login");
		return modelAndView;
	}
}
